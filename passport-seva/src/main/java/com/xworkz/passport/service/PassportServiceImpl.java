package com.xworkz.passport.service;

import com.xworkz.passport.dto.PassportDTO;
import com.xworkz.passport.entity.PassportEntity;
import com.xworkz.passport.repository.PassportRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassportServiceImpl implements PassportService{

    @Autowired
    private PassportRepository passportRepository;

    public PassportServiceImpl()
    {
        System.out.println("PassportServiceImpl constructor");
    }

    public boolean validateDetails(PassportDTO passportDTO)
    {
        System.out.println("validateDetails method in service");
        if(passportDTO!=null)
        {
            System.out.println("passportDto is valid");
        }else {
            System.out.println("passportDto is not valid");
            return false;
        }

        if(passportDTO.getPassword().equals(passportDTO.getConfirmPassword()))
        {
            System.out.println("password and confirm password is equal");
        }else {
            System.out.println("password and confirm password is not equal");
            return false;
        }
        if(passportDTO.getLoginSameAsEmail().equals("yes"))
        {
            if(passportDTO.getEmail().equals(passportDTO.getLoginId()))
            {
                System.out.println("Email and loginId is same");
            }else {
                System.out.println("Email and loginId is not same");
            }
        }
        return true;
    }
    @Override
    public boolean save(PassportDTO passportDTO) {
        System.out.println("save method in service");
        System.out.println("service data: "+passportDTO);
        if(validateDetails(passportDTO)) {
            PassportEntity entity = new PassportEntity();
            BeanUtils.copyProperties(passportDTO, entity);
            return passportRepository.save(entity);
        }
        return false;
    }

    @Override
    public List<PassportDTO> findAll() {
        System.out.println("findAll method in service");
        List<PassportEntity> listEntity=passportRepository.findAll();
        List<PassportDTO> listDto=new ArrayList<>();
        for (PassportEntity entity : listEntity) {
            PassportDTO dto = new PassportDTO();
            BeanUtils.copyProperties(entity, dto);
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public String findExistingEmail(String email) {
        System.out.println("findExistingEmail method in service");
        return passportRepository.findExistingEmail(email);
    }

    @Override
    public Long findExistingPhoneNumber(Long phoneNumber) {
        System.out.println("findExistingPhoneNumber method in service");
        return passportRepository.findExistingPhoneNumber(phoneNumber);
    }

    @Override
    public String findExistingLoginId(String loginId) {
        System.out.println("findExistingLoginId method in service");
        return passportRepository.findExistingLoginId(loginId);
    }

    @Override
    public PassportDTO findByPassportId(Integer id) {
        System.out.println("findByPassportId method in service");
        PassportEntity entity=passportRepository.findByPassportId(id);
        PassportDTO dto=new PassportDTO();
        BeanUtils.copyProperties(entity,dto);
        return dto;
    }

    @Override
    public String updatePassportById(PassportDTO dto) {
        System.out.println("updatePassportById method in service");
        PassportEntity entity=new PassportEntity();
        BeanUtils.copyProperties(dto,entity);
        if(passportRepository.updatePassportEntityById(entity))
            return "Data updated";
        return "Data not updated";
    }

    @Override
    public String deleteUserById(Integer id) {
        System.out.println("deleteUserById method in service");
        if(passportRepository.deleteUserById(id))
            return "Data deleted";
        return "Data not deleted";
    }

    @Override
    public List<PassportDTO> searchUserByUserName(String name) {
        System.out.println("searchUserByUserName method in service");
        List<PassportEntity> entities=passportRepository.searchUserByUserName(name);
        List<PassportDTO> dtos=new ArrayList<>();
        for(PassportEntity entity:entities)
        {
            PassportDTO dto=new PassportDTO();
            BeanUtils.copyProperties(entity,dto);
            dtos.add(dto);
        }
        return dtos;
    }
}
