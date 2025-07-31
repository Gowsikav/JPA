package com.xworkz.water.service;

import com.xworkz.water.entity.ApplicationEntity;

import java.util.Optional;

public interface ApplicationService {
    boolean validate(ApplicationEntity applicationEntity);

    Optional<ApplicationEntity> findById(int id);

    boolean deleteById(int id);

    boolean updateById(int id,String company);
}
