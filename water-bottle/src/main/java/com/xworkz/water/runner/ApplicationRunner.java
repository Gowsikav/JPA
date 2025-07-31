package com.xworkz.water.runner;

import com.xworkz.water.entity.ApplicationEntity;
import com.xworkz.water.service.ApplicationService;
import com.xworkz.water.service.ApplicationServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

public class ApplicationRunner {
    public static void main(String[] args) {
        ApplicationEntity applicationEntity=new ApplicationEntity();
        applicationEntity.setApplicationName("Zomato");
        applicationEntity.setApplicationSize("2 GB");
        applicationEntity.setCompany("x-workz");
        applicationEntity.setNoOfUsers(200);
        applicationEntity.setRatings(4.5f);
        applicationEntity.setLaunchDate(LocalDate.of(2020,6,20));

        ApplicationService applicationService=new ApplicationServiceImpl();
        if(applicationService.validate(applicationEntity))
        {
            System.out.println("Data saved");
        }
        else {
            System.out.println("Invalid details");
        }

        Optional<ApplicationEntity> optionalApplicationEntity=applicationService.findById(1);
        optionalApplicationEntity.ifPresent(System.out::println);

        if(applicationService.deleteById(4))
        {
            System.out.println("id is deleted");
        }
        else {
            System.out.println("id not deleted");
        }

        if(applicationService.updateById(3,"Wipro"))
        {
            System.out.println("Updated the company name in id ");
        }
        else {
            System.out.println("Not updated");
        }
    }
}
