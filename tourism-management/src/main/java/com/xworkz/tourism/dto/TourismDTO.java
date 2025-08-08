package com.xworkz.tourism.dto;


import lombok.Data;

@Data
public class TourismDTO {

    private Integer packageId;
    private String packageName;
    private String destination;
    private Integer days;
    private Double packagePrice;
    private Integer personsCount;
}
