package com.xworkz.user.service;

import com.xworkz.user.dto.RegisterDTO;

public interface RegisterService {

    boolean save(RegisterDTO registerDTO);
    String checkExistingEmail(String email);
    String checkExistingPhoneNumber(String phoneNumber);
    RegisterDTO getUserDetails(String email, String password);
}
