package com.xworkz.passport.service;

import com.xworkz.passport.dto.PassportDTO;

import java.util.List;

public interface PassportService {

    boolean save(PassportDTO passportDTO);
    List<PassportDTO> findAll();
    String findExistingEmail(String email);
    Long findExistingPhoneNumber(Long phoneNumber);
    String findExistingLoginId(String loginId);
}
