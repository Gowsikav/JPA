package com.xworkz.passport.repository;

import com.xworkz.passport.entity.PassportEntity;

import java.util.List;

public interface PassportRepository {

    boolean save(PassportEntity passport);
    List<PassportEntity> findAll();
    String findExistingEmail(String email);
    Long findExistingPhoneNumber(Long phoneNumber);
    String findExistingLoginId(String loginId);

}
