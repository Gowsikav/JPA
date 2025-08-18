package com.xworkz.user.repository;

import com.xworkz.user.entity.RegisterEntity;

public interface RegisterRepository {

    boolean save(RegisterEntity entity);
    String checkExistingEmail(String email);
    String checkExistingPhoneNumber(String phoneNumber);
    RegisterEntity getUserDetailsByEmail(String email);

}
