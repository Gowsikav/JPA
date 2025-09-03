package com.xworkz.user.service;

import com.xworkz.user.dto.RegisterDTO;
import com.xworkz.user.entity.RegisterEntity;
import com.xworkz.user.repository.RegisterRepository;
import com.xworkz.user.util.OTPUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private EmailSender emailSender;

    private Map<String, Integer> failedAttempts = new HashMap<>();

    public RegisterServiceImpl() {
        System.out.println("RegisterServiceImpl constructor");
    }

    @Override
    public boolean save(RegisterDTO registerDTO) {
        System.out.println("save method in RegisterServiceImpl");
        System.out.println("service data: " + registerDTO);
        System.out.println("All details are valid");
        RegisterEntity entity = new RegisterEntity();
        BeanUtils.copyProperties(registerDTO, entity);
        String otp=OTPUtil.generateNumericOtp(6);
        entity.setPassword(otp);
        entity.setIsActive(true);
        entity.setLockTime(null);
        entity.setLoginCount(-1);
        if(repository.save(entity))
        {
            if(emailSender.mailSend(registerDTO.getEmail(),otp))
            {
                System.out.println("Email send");
                return true;
            }
            System.out.println("Email not send");
            return false;
        }
        System.out.println("Data not saved");
        return false;
    }

    @Override
    public String checkExistingPhoneNumber(String phoneNumber) {
        System.out.println("checkExistingPhoneNumber method in service");
        return repository.checkExistingPhoneNumber(phoneNumber);
    }

    @Override
    public String checkExistingEmail(String email) {
        System.out.println("checkExistingEmail method in service");
        return repository.checkExistingEmail(email);
    }

    @Override
    public RegisterDTO getUserDetails(String email, String password) {
        System.out.println("getUserDetails method in service");
        RegisterEntity entity = repository.getUserDetailsByEmail(email);

        if (entity == null) {
            return null;
        }

        if(entity.getLoginCount()==-1)
        {
            if(entity.getPassword().equals(password))
            {
                RegisterDTO dto=new RegisterDTO();
                BeanUtils.copyProperties(entity,dto);
                String baseUrl="http://localhost:8081/uploads/";
                dto.setProfilePath(baseUrl+entity.getProfilePath());
                return dto;
            }
        }

        if (entity.getLockTime() != null && entity.getLockTime().isAfter(LocalDateTime.now())) {
            throw new RuntimeException("Account locked. Try again later. Unlock time: " + entity.getLockTime());
        }

        if (passwordEncoder.matches(password, entity.getPassword())) {
            failedAttempts.put(email, 0);
            entity.setLockTime(null);

            repository.mergeLockTime(entity);
            RegisterDTO dto=new RegisterDTO();
            BeanUtils.copyProperties(entity,dto);
            return dto;
        }
        else {
            int count = failedAttempts.getOrDefault(email, 0) + 1;
            failedAttempts.put(email, count);

            if (count >= 3) {
                entity.setLockTime(LocalDateTime.now().plusHours(24));
                repository.mergeLockTime(entity);
                failedAttempts.put(email, 0);
                throw new RuntimeException("Account locked for 24 Hours.");
            }
            return null;
        }
    }

    @Override
    public boolean compareOtp(String email, String otp) {
        System.out.println("compareOtp method in service");
        String dbOtp= repository.getOtp(email);
        return dbOtp.equals(otp);
    }

    @Override
    public boolean setPasswordByEmail(String email,String password, String confirmPassword) {
        System.out.println("setPasswordByEmail method in service");
        if(password.equals(confirmPassword))
        {
            password=passwordEncoder.encode(password);
            if(repository.setPassword(email,password))
            {
                System.out.println("Password saved");
                return true;
            }
            System.out.println("password not saved");
        }
        System.out.println("Password not matched");
        return false;
    }

    @Override
    public RegisterDTO getUserDetailsByEmail(String email) {
        System.out.println("getUserDetailsByEmail method in service");
        RegisterEntity entity=repository.getUserDetailsByEmail(email);
        RegisterDTO dto=new RegisterDTO();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

    @Override
    public boolean updateUserDetails(RegisterDTO registerDTO) {
        System.out.println("updateUserDetails method in service");
        RegisterEntity entity=new RegisterEntity();
        BeanUtils.copyProperties(registerDTO,entity);
        return repository.updateUserDetails(entity);
    }

    @Override
    public boolean setOTPByEmail(String email) {
        System.out.println("setOTPByEmail method in service");
        String generatedNumericOtp=OTPUtil.generateNumericOtp(6);
        if(repository.updateOTPByEmail(email,generatedNumericOtp))
        {
            if(emailSender.mailSend(email,generatedNumericOtp))
            {
                System.out.println("otp sent");
                return true;
            }
            System.out.println("otp not updated");
        }

        return false;
    }

    @Override
    public List<String> getAllEmail() {
        System.out.println("getAllEmail method in service");
        return repository.getAllEmail();
    }
}