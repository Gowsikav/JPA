package com.xworkz.water.service;

import com.xworkz.water.entity.ApplicationEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface ApplicationService {
    boolean validate(ApplicationEntity applicationEntity);

    Optional<ApplicationEntity> findById(int id);

    boolean deleteById(int id);

    boolean updateById(int id,String company);

    void findByApplicationName(String applicationName);

    void findByApplicationSize(String applicationSize);

    void findByApplicationCompany(String applicationCompany);

    void findByApplicationUsersCount(Integer applicationUsersCount);

    void findByApplicationRatings(Float applicationRatings);

    void findByApplicationLaunchDate(LocalDate applicationLaunchDate);


}
