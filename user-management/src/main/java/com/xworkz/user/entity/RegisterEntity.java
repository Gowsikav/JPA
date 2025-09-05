package com.xworkz.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_register_details")
@NamedQuery(name = "checkExistingEmail",query ="select a.email from RegisterEntity a where a.email=:email and a.isActive=true")
@NamedQuery(name = "checkExistingPhoneNumber",query ="select a.phoneNumber from RegisterEntity a where a.phoneNumber=:phoneNumber and a.isActive=true")
@NamedQuery(name = "getDetailsByEmail",query = "select a from RegisterEntity a where a.email=:email and a.isActive=true")
@NamedQuery(name = "getOtp",query = "select a.password from RegisterEntity a where a.email=:email and a.isActive=true")
@NamedQuery(name = "setPassword",query = "update RegisterEntity a set a.password=:password ,a.loginCount=0 where a.email=:email and a.isActive=true")
@NamedQuery(name = "setOTP",query = "update RegisterEntity a set a.password=:otp where a.email=:email and a.isActive=true")
@NamedQuery(name = "getAllEmail",query = "select a.email from RegisterEntity a")
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "register_id")
    private Integer registerId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "state")
    private String state;

    @Column(name = "address")
    private String address;

    @Column(name = "district")
    private String district;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "login_count")
    private Integer loginCount;

    @Column(name = "lock_time",columnDefinition = "DATETIME")
    private LocalDateTime lockTime;

    @Column(name = "profile_path")
    private String profilePath;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "registerEntity")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private AuditEntity audit;

    @PrePersist
    public void prePersist() {
        if (audit == null) {
            audit = new AuditEntity();
            audit.setRegisterEntity(this);
        }
        audit.setCreatedBy(this.userName);
        audit.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        if (audit == null) {
            audit = new AuditEntity();
            audit.setRegisterEntity(this);
        }
        audit.setModifiedBy(this.userName);
        audit.setModifiedAt(LocalDateTime.now());
    }
}
