package com.company.addonis.services;

import com.company.addonis.models.User;
import com.company.addonis.services.contracts.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {

    public static final String SENDER_MAIL = "ivan.ventsislavov.mail@gmail.com";

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(User user) {
        String receiver = user.getEmail();
        String subject = "Complete Registration!";
        String text = "To confirm your account, please click here : " +
                "http://localhost:8080/auth/confirm/";
        sendEmail(receiver, subject, text);
    }

    @Override
    public void sendEmail(String receiver, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(receiver);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(SENDER_MAIL);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }

    @Override
    public void inviteFriend(String friendEmail) {
        if (!(friendEmail.endsWith("@gmail.com") || friendEmail.endsWith("@abv.bg"))) {
            throw new UnsupportedOperationException("Email should be in gmail.com or abv.bg.");
        }
        String subject = "Come and register!";
        String text = "To become one of us, please click here : " +
                "http://localhost:8080/auth/register";
        sendEmail(friendEmail, subject, text);
    }
}
