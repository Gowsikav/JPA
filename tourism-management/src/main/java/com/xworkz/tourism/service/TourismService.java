package com.xworkz.tourism.service;

import com.xworkz.tourism.dto.TourismDTO;

import java.util.List;
import java.util.Optional;

public interface TourismService {

    boolean validate(TourismDTO tourismDTO);
    List<TourismDTO> getAllEntity();
    Optional<TourismDTO> findById(Integer id);
}
