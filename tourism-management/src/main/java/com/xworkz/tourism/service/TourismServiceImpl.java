package com.xworkz.tourism.service;

import com.xworkz.tourism.dto.TourismDTO;
import com.xworkz.tourism.entity.TourismEntity;
import com.xworkz.tourism.repository.TourismRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TourismServiceImpl implements TourismService{

    @Autowired
    private TourismRepository tourismRepository;

    public TourismServiceImpl()
    {
        System.out.println("TourismServiceImpl constructor");
    }

    public boolean validateDetails(TourismDTO tourismDTO) {
        System.out.println("validateDetails method in service");
        if(tourismDTO!=null)
        {
            System.out.println("tourism dto is valid");
        }else {
            System.out.println("tourism dto is not valid");
            return false;
        }
        String name=tourismDTO.getPackageName();
        if(name!=null && !name.isEmpty())
        {
            System.out.println("Package name is valid");
        }else {
            System.out.println("Package name is not valid");
return false;
        }

        String destination=tourismDTO.getDestination();
        if(destination!=null && !destination.isEmpty())
        {
            System.out.println("Destination is valid");
        }else {
            System.out.println("Destination is not valid");
            return false;
        }

        Double price=tourismDTO.getPackagePrice();
        if(price!=null && price>100)
        {
            System.out.println("price is valid");
        }else {
            System.out.println("price is not valid");
            return false;
        }

        Integer count=tourismDTO.getPersonsCount();
        if(count!=null && count>0)
        {
            System.out.println("person count is valid");
        }else {
            System.out.println("person count is not valid");
            return false;
        }

        Integer days=tourismDTO.getDays();
        if(days!=null && days>0)
        {
            System.out.println("days is valid");
        }else {
            System.out.println("days is not valid");
            return false;
        }
        System.out.println("All details are valid");
        return true;
    }

    @Override
    public boolean validate(TourismDTO tourismDTO) {
        System.out.println("validate method in service");
        System.out.println("Service data: "+tourismDTO);

        if(validateDetails(tourismDTO)) {

            TourismEntity tourism = new TourismEntity();
            tourism.setPackageName(tourismDTO.getPackageName());
            tourism.setDestination(tourismDTO.getDestination());
            tourism.setDays(tourismDTO.getDays());
            tourism.setPackagePrice(tourismDTO.getPackagePrice());
            tourism.setPersonsCount(tourismDTO.getPersonsCount());

            return tourismRepository.save(tourism);
        }
        System.out.println("Invalid details");
        return false;
    }

    @Override
    public List<TourismDTO> getAllEntity() {
        System.out.println("getAllEntity method in service");
        List<TourismEntity> listOfTourismEntity=tourismRepository.getAllEntity();
        List<TourismDTO> listOfTourismDto=listOfTourismEntity.stream()
                .map(entity -> {
                    TourismDTO dto = new TourismDTO();
                    dto.setPackageId(entity.getPackageId());
                    dto.setPackagePrice(entity.getPackagePrice());
                    dto.setDays(entity.getDays());
                    dto.setDestination(entity.getDestination());
                    dto.setPackageName(entity.getPackageName());
                    dto.setPersonsCount(entity.getPersonsCount());
                    return dto;
                })
                .collect(Collectors.toList());

        return listOfTourismDto;
    }
}
