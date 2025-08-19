package com.xworkz.user.service;

import com.xworkz.user.config.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    @Autowired
    private EmailConfiguration configuration;

    public EmailSenderImpl()
    {
        System.out.println("EmailSenderImpl constructor");
    }

    @Override
    public boolean mailSend(String email, String otp) {
        System.out.println("mailSend method");
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("Otp to user for verification");
            simpleMailMessage.setText("Otp for verification " + otp);
            configuration.mailSender().send(simpleMailMessage);
            System.out.println("mail sent to :" + email + " - OTP : " + otp);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

}
