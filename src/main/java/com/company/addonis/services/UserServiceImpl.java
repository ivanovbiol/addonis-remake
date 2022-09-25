package com.company.addonis.services;

import com.company.addonis.models.User;
import com.company.addonis.repositories.AddonRepository;
import com.company.addonis.repositories.UserRepository;
import com.company.addonis.services.contracts.EmailSenderService;
import com.company.addonis.services.contracts.UserService;
import com.company.addonis.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    public static final String USER_WITH_FIELD_ALREADY_EXIST_TEMPLATE = "User with %s %s already exist!";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PHONE_NUMBER = "phone number";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddonRepository addonRepository;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserUtils userUtils;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(int id) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(element -> userUtils.setUserImageString(element));
        user.ifPresent(currentUser ->
                currentUser.setUploadedAddons(addonRepository.findByCreatorId(currentUser.getId()).size()));
        return user;
    }

    @Override
    public Optional<?> save(User user) {
        Optional<String> errorMessage = validateUserInfo(user);
        if (errorMessage.isPresent()) {
            return errorMessage;
        }
        Optional<User> savedUser = Optional.of(userRepository.save(user));
//        emailSenderService.sendEmail(user);
        return savedUser;
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> block(User user) {
        Optional<User> userToBlock = userRepository.findById(user.getId());
        if (userToBlock.isEmpty()) {
            return Optional.empty();
        }
        userToBlock.get().setEnabled(false);
        return Optional.of(userRepository.save(userToBlock.get()));
    }

    @Override
    public Optional<User> unblock(User user) {
        Optional<User> userToUnBlock = userRepository.findById(user.getId());
        if (userToUnBlock.isEmpty()) {
            return Optional.empty();
        }
        userToUnBlock.get().setEnabled(true);
        return Optional.of(userRepository.save(userToUnBlock.get()));
    }

    private Optional<String> validateUserInfo(User user) {
        Optional<User> userExist = userRepository.findByUsername(user.getUsername());
        if (userExist.isPresent()) {
            return Optional.of(String.format(USER_WITH_FIELD_ALREADY_EXIST_TEMPLATE,
                    USERNAME, user.getUsername()));
        }
        userExist = userRepository.findByEmail(user.getEmail());
        if (userExist.isPresent()) {
            return Optional.of(String.format(USER_WITH_FIELD_ALREADY_EXIST_TEMPLATE,
                    EMAIL, user.getEmail()));
        }
        userExist = userRepository.findByPhoneNumber(user.getUsername());
        if (userExist.isPresent()) {
            return Optional.of(String.format(USER_WITH_FIELD_ALREADY_EXIST_TEMPLATE,
                    PHONE_NUMBER, user.getPhoneNumber()));
        }
        return Optional.empty();
    }
}