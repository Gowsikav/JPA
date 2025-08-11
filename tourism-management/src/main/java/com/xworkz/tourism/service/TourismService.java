package com.xworkz.tourism.service;

import com.xworkz.tourism.dto.TourismDTO;

import java.util.List;

public interface TourismService {

    boolean validate(TourismDTO tourismDTO);
    List<TourismDTO> getAllEntity();
}
