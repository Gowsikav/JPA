package com.xworkz.user.service;

import com.xworkz.user.dto.RegisterDTO;

public interface RegisterService {

    boolean save(RegisterDTO registerDTO);
    String checkExistingEmail(String email);
    String checkExistingPhoneNumber(String phoneNumber);
    RegisterDTO getUserDetails(String email, String password);
    boolean compareOtp(String email,String otp);
    boolean setPasswordByEmail(String email,String password,String confirmPassword);
    RegisterDTO getUserDetailsByEmail(String email);
    boolean updateUserDetails(RegisterDTO registerDTO);
    boolean setOTPByEmail(String email);
}
