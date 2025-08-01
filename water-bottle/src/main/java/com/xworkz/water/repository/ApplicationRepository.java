package com.xworkz.water.repository;

import com.xworkz.water.entity.ApplicationEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface ApplicationRepository {

    boolean saveApplication(ApplicationEntity applicationEntity);

    Optional<ApplicationEntity> findById(int id);

    boolean deleteById(int id);

    boolean updateById(int id,String company);

    void findByApplicationName(String applicationName);

    void findByApplicationSize(String applicationSize);

    void findByApplicationUsersCount(Integer applicationUsersCount);

    void findByApplicationRatings(Float applicationRatings);

    void findByApplicationLaunchDate(LocalDate applicationLaunchDate);
    void findByApplicationCompany(String applicationCompany);
}
