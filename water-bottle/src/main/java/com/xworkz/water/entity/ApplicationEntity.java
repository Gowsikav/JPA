package com.xworkz.water.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "application_info")
@NamedQuery(name = "applicationName",
        query = "select a from ApplicationEntity a where a.applicationName=:name")
@NamedQuery(name = "applicationSize",
        query = "select a from ApplicationEntity a where a.applicationSize=:size")
@NamedQuery(name = "applicationCompany",
        query = "select a from ApplicationEntity a where a.company=:company")
@NamedQuery(name = "applicationUsers",
        query = "select a from ApplicationEntity a where a.noOfUsers=:usersCount")
@NamedQuery(name = "applicationRatings",
        query = "select a from ApplicationEntity a where a.ratings=:ratings")
@NamedQuery(name = "applicationLaunchDate",
        query = "select a from ApplicationEntity a where a.launchDate=:launchDate")
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Integer applicationId;

    @Column(name = "application_name")
    private String applicationName;

    @Column(name = "application_size")
    private String applicationSize;

    @Column(name = "company")
    private String company;

    @Column(name = "no_of_users")
    private Integer noOfUsers;

    @Column(name = "ratings")
    private Float ratings;

    @Column(name = "launch_date")
    private LocalDate launchDate;

}
