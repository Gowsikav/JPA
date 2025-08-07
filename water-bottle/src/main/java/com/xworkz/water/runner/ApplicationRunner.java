package com.xworkz.water.runner;

import com.xworkz.water.entity.ApplicationEntity;
import com.xworkz.water.service.ApplicationService;
import com.xworkz.water.service.ApplicationServiceImpl;
import com.xworkz.water.util.JPAConnection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ApplicationRunner {
    public static void main(String[] args) {
        ApplicationService applicationService=new ApplicationServiceImpl();

//        ApplicationEntity applicationEntity=new ApplicationEntity();
//        applicationEntity.setApplicationName("Filpkart");
//        applicationEntity.setApplicationSize("3 GB");
//        applicationEntity.setCompany("TCS");
//        applicationEntity.setNoOfUsers(150);
//        applicationEntity.setRatings(4.0f);
//        applicationEntity.setLaunchDate(LocalDate.of(2021,8,10));
//
//        if(applicationService.validate(applicationEntity))
//        {
//            System.out.println("Data saved");
//        }
//        else {
//            System.out.println("Invalid details");
//        }
//
//        Optional<ApplicationEntity> optionalApplicationEntity=applicationService.findById(3);
//        optionalApplicationEntity.ifPresent(System.out::println);
//
//        if(applicationService.deleteById(3))
//        {
//            System.out.println("id is deleted");
//        }
//        else {
//            System.out.println("id not deleted");
//        }
//
//        if(applicationService.updateById(2,"Wipro"))
//        {
//            System.out.println("Updated the company name in id ");
//        }
//        else {
//            System.out.println("Not updated");
//        }
//
//        applicationService.findByApplicationName("Filpkart");
//        applicationService.findByApplicationSize("12 GB");
//        applicationService.findByApplicationUsersCount(100);
//        applicationService.findByApplicationCompany("rapido company");
//        applicationService.findByApplicationRatings(4f);
//        applicationService.findByApplicationLaunchDate(LocalDate.of(2017,3,7));
//
//        List<ApplicationEntity> list=applicationService.findAll();
//        if(list!=null)
//        {
//            list.forEach(System.out::println);
//        }else {
//            System.out.println("list is empty");
//        }
//
//        String companyName="TCS";
//        ApplicationEntity applicationEntity1=applicationService.findByCompany(companyName);
//       if(applicationEntity1!=null)
//           System.out.println(applicationEntity1);

//        List<Object[]> objects=applicationService.getAllCompanyAndUsersCount();
//        objects.stream().map(e->e[0]+" : "+e[1]).forEach(System.out::println);

        List<String[]> strings=applicationService.getAllApplicationNameAndCompany();
        List<String[]> results = new ArrayList<>();

        for (Object[] row : strings) {
            String[] stringRow = new String[row.length];
            for (int i = 0; i < row.length; i++) {
                stringRow[i] = row[i] != null ? row[i].toString() : null;
            }
            results.add(stringRow);
        }

        results.stream().map(e->e[0]+" : "+e[1]).forEach(System.out::println);

        JPAConnection.closeResource();
    }
}
