package com.company.addonis.services;

import com.company.addonis.models.Addon;
import com.company.addonis.repositories.AddonRepository;
import com.company.addonis.repositories.StatusRepository;
import com.company.addonis.services.contracts.AddonService;
import com.company.addonis.services.contracts.GitHubDataService;
import com.company.addonis.utils.AddonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddonServiceImpl implements AddonService {

    @Autowired
    GitHubDataService gitHubDataService;
    @Autowired
    private AddonRepository addonRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private AddonUtils addonUtils;

    @Override
    public List<Addon> findAll() {
        return addonRepository.findAll();
    }

    @Override
    public Optional<Addon> findById(int id) {
        Optional<Addon> addon = addonRepository.findById(id);
        addon.ifPresent(element -> addonUtils.setAddonImageString(element));
        addon.ifPresent(element -> addonUtils.setAddonTotalScore(element));
        addon.ifPresent(element -> addonUtils.setAddonCreatorImageString(element));
        return addon;
    }

    @Override
    public Optional<?> save(Addon addon) {
        Optional<String> errorMessage = validateAddonName(addon);
        if (errorMessage.isPresent()) {
            return errorMessage;
        }
        try {
            gitHubDataService.addGitHubData(addon);
        } catch (IllegalAccessException e) {
            return Optional.of("Invalid token");
        }
        return Optional.of(addonRepository.save(addon));
    }

    @Override
    public void delete(Addon addon) {
        addonRepository.delete(addon);
    }

    @Override
    public Optional<?> approve(Addon addon) {
        Optional<Addon> addonToApprove = addonRepository.findById(addon.getId());
        if (addonToApprove.isEmpty()) {
            return Optional.empty();
        }
        // Set status approved
        addonToApprove.get().setStatus(statusRepository.findById(2).get());
        return Optional.of(addonRepository.save(addonToApprove.get()));
    }

    @Override
    public Optional<?> feature(Addon addon) {
        return Optional.empty();
    }

    private Optional<String> validateAddonName(Addon addon) {
        Optional<Addon> addonExist = addonRepository.findByName(addon.getName());
        if (addonExist.isPresent() && addonExist.get().getId() != addon.getId()) {
            return Optional.of(String.format("Addon with name %s already exist!", addonExist.get().getName()));
        }
        return Optional.empty();
    }
}