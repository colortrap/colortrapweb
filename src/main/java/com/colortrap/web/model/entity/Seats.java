package com.colortrap.web.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Seats {

    private int seatsCountMax;

    private int seatsToShow;
    
    private int takenSeats;

}
