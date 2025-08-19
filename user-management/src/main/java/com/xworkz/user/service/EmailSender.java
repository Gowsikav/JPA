package com.xworkz.user.service;

public interface EmailSender {

    boolean mailSend(String email,String otp);
}
