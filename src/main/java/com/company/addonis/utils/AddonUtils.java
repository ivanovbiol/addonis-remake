package com.company.addonis.utils;

import com.company.addonis.models.Addon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class AddonUtils {

    @Autowired
    private UserUtils userUtils;

    public void setBinaryData(Addon addon, MultipartFile file, MultipartFile image) throws IOException {
        if (image.isEmpty() || file.isEmpty()) {
            throw new FileNotFoundException("Image or Content are empty");
        }
        addon.setImage(image.getBytes());
        addon.setBinaryContent(file.getBytes());
    }

    public void setRating(Addon addon, int score) {
        addon.setTotalScore(addon.getTotalScore() + score);
        addon.setTotalVoters(addon.getTotalVoters() + 1);
    }

    public void setAddonImageString(List<Addon> addons) {
        addons.forEach(addon -> addon.setImageString(getAddonImage(addon)));
    }

    public void setAddonImageString(Addon addon) {
        addon.setImageString(getAddonImage(addon));
    }

    public void setAddonTotalScore(Addon addon) {
        addon.setCalculatedScore(addon.getTotalScore() / (addon.getTotalVoters() * 1.0));
    }

    public void setAddonTotalScore(List<Addon> addons) {
        addons.forEach(addon -> addon.setCalculatedScore(addon.getTotalScore() / (addon.getTotalVoters() * 1.0)));
    }

    public void setAddonCreatorImageString(List<Addon> addons) {
        addons.forEach(addon -> userUtils.setUserImageString(addon.getCreator()));
    }

    public void setAddonCreatorImageString(Addon addon) {
        userUtils.setUserImageString(addon.getCreator());
    }

    public String getAddonImage(Addon addon) {
        if (addon.getImage() == null) {
            return null;
        }
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(addon.getImage());
    }
}