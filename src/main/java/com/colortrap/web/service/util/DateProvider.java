package com.colortrap.web.service.util;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DateProvider {
    public LocalDate getDate(){
        return LocalDate.now();
    }
}
