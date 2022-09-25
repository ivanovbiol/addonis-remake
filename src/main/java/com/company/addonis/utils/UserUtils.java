package com.company.addonis.utils;

import com.company.addonis.models.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Component
public class UserUtils {
    public void setUserImageString(User user) {
        user.setImage(getUserImage(user));
    }

    public void setUserImageString(List<User> users) {
        users.forEach(user -> user.setImage(getUserImage(user)));
    }

    public void setUserDefaultPhoto(User user) throws IOException {
        File file = new ClassPathResource("static/default-user-photo/user_default_pic.png").getFile();
        FileInputStream fileInputStream = new FileInputStream(file);
        user.setPhoto(fileInputStream.readAllBytes());
        fileInputStream.close();
    }

    public String getUserImage(User user) {
        if (user.getPhoto() == null) {
            return null;
        }
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(user.getPhoto());
    }
}