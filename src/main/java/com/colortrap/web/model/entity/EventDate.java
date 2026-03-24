package com.colortrap.web.model.entity;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EventDate {    
    private int startDay;

    private int startMonth;

    private int startYear;
    
    private int endDay;

    private int endMonth;

    private int endYear;

    public LocalDate getStartDate(){
        return LocalDate.of(Integer.parseInt(String.valueOf(startYear)), Integer.parseInt(String.valueOf(startMonth)), Integer.parseInt(String.valueOf(startDay)));
    }

    public LocalDate getEndDate(){
        if(endDay != 0 && endMonth != 0 && endYear != 0){
        return LocalDate.of(Integer.parseInt(String.valueOf(endYear)), Integer.parseInt(String.valueOf(endMonth)), Integer.parseInt(String.valueOf(endDay)));
    }
    return getStartDate();
    }
}
