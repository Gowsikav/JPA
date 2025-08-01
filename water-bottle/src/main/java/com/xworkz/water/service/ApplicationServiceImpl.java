package com.xworkz.water.service;

import com.xworkz.water.entity.ApplicationEntity;
import com.xworkz.water.repository.ApplicationRepository;
import com.xworkz.water.repository.ApplicationRepositoryImpl;

import java.time.LocalDate;
import java.util.Optional;

public class ApplicationServiceImpl implements ApplicationService{

    ApplicationRepository applicationRepository=new ApplicationRepositoryImpl();
    public ApplicationServiceImpl()
    {
        System.out.println("ApplicationService Implementation constructor");
    }

    public boolean validateDetails(ApplicationEntity applicationEntity) {
        System.out.println("validateDetails method in ApplicationService Implementation");

        if(applicationEntity!=null)
        {
            System.out.println("Application entity is not null");
        }
        else {
            System.out.println("Application entity is null");
            return false;
        }

        String applicationName= applicationEntity.getApplicationName();
        System.out.println(applicationName);
        if(applicationName!=null)
        {
            System.out.println("name is valid");
        }else {
            System.out.println("name is invalid");
            return false;
        }

        String size=applicationEntity.getApplicationSize();
        if(size!=null)
        {
            System.out.println("size is valid");
        }else {
            System.out.println("size is invalid");
            return false;
        }

        String company=applicationEntity.getCompany();
        if(company!=null)
        {
            System.out.println("company is valid");
        }
        else {
            System.out.println("company is Invalid");
            return false;
        }

        int usersCount=applicationEntity.getNoOfUsers();
        if(usersCount<0)
        {
            System.out.println("No of users is invalid");
            return false;
        }else {
            System.out.println("No of users is valid");
        }

        float ratings= applicationEntity.getRatings();
        if(ratings<0)
        {
            System.out.println("Ratings is invalid");
            return false;
        }
        else {
            System.out.println("Ratings is valid");
        }

        LocalDate launchDate=applicationEntity.getLaunchDate();
        if(launchDate!=null)
        {
            System.out.println("LaunchDate is valid");
        }
        else {
            System.out.println("LaunchDate is invalid");
            return false;
        }

        return true;
    }

    @Override
    public boolean validate(ApplicationEntity applicationEntity) {
        System.out.println("validateDetails method in ApplicationService Implementation");

        validateDetails(applicationEntity);
        System.out.println("All details are valid");

        return applicationRepository.saveApplication(applicationEntity);
    }

    @Override
    public Optional<ApplicationEntity> findById(int id) {
        System.out.println("findById method in ApplicationService Implementation");

        if(id>0)
        {
            System.out.println("id is valid");
          Optional<ApplicationEntity> optionalApplicationEntity=applicationRepository.findById(id);
          if(optionalApplicationEntity.isPresent())
          {
              ApplicationEntity applicationEntity=optionalApplicationEntity.get();
             if(validateDetails(applicationEntity))
              {
                  System.out.println("Valid details");
                  return Optional.of(applicationEntity);
              }
             else {
                 System.out.println("Invalid details");
             }
          }
          else {
              System.out.println("Application entity is not present");
          }
        }else {
            System.out.println("invalid id");
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        System.out.println("deleteById method in ApplicationService Implementation");

        if(id>0)
        {
            System.out.println("Id is valid");
            return applicationRepository.deleteById(id);
        }else {
            System.out.println("id is invalid");
        }
        return false;
    }

    @Override
    public boolean updateById(int id, String company) {
        System.out.println("updateById method in ApplicationService Implementation");

        if(id>0)
        {
            System.out.println("id is valid");
        }
        else {
            System.out.println("id is not valid");
            return false;
        }
        if(company==null)
        {
            System.out.println("company is invalid");
            return false;
        }
        else {
            System.out.println("company is valid");
            if(applicationRepository.updateById(id,company))
            {
                System.out.println("company is updated");
            }else {
                System.out.println("company is not updated");
                return false;
            }
        }
        return true;

    }

    @Override
    public void findByApplicationName(String applicationName) {
        System.out.println("findByApplicationName in ApplicationService Implementation");
        if(applicationName!=null)
        {
            applicationRepository.findByApplicationName(applicationName);
        }
        else{
            System.out.println("applicationName is null");
        }

    }

    @Override
    public void findByApplicationSize(String applicationSize) {
        System.out.println("findByApplicationSize in ApplicationService Implementation");
        if(applicationSize!=null)
        {
            applicationRepository.findByApplicationSize(applicationSize);
        }
        else{
            System.out.println("applicationSize is null");
        }
    }

    @Override
    public void findByApplicationUsersCount(Integer applicationUsersCount) {
        System.out.println("findByApplicationUsersCount in ApplicationService Implementation");
        if(applicationUsersCount>0)
        {
            applicationRepository.findByApplicationUsersCount(applicationUsersCount);
        }
        else{
            System.out.println("applicationUsersCount is less than 0");
        }
    }

    @Override
    public void findByApplicationRatings(Float applicationRatings) {
        System.out.println("findByApplicationRatings in ApplicationService Implementation");
        if(applicationRatings>0)
        {
            applicationRepository.findByApplicationRatings(applicationRatings);
        }
        else{
            System.out.println("applicationRatings is less than 0");
        }
    }

    @Override
    public void findByApplicationLaunchDate(LocalDate applicationLaunchDate) {
        System.out.println("findByApplicationLaunchDate in ApplicationService Implementation");
        if(applicationLaunchDate!=null)
        {
            applicationRepository.findByApplicationLaunchDate(applicationLaunchDate);
        }
        else{
            System.out.println("applicationLaunchDate is null");
        }
    }

    @Override
    public void findByApplicationCompany(String applicationCompany) {
        System.out.println("findByApplicationCompany in ApplicationService Implementation");
        if(applicationCompany!=null)
        {
            applicationRepository.findByApplicationCompany(applicationCompany);
        }
        else{
            System.out.println("applicationCompany is null");
        }
    }
}
