package com.colortrap.web.model.dto;

import com.colortrap.web.model.enumeration.SuitableForEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkshopDTO {
    
    private int startDay;

    private int startMonth;

    private int startYear;
    
    private int endDay;

    private int endMonth;

    private int endYear;

    private String startAt;

    private String endAt;
    
    private SuitableForEnum suitableFor;

    private String eventType;

    private String description;

    private String title;

    private String price; 
    
    private String discountedPrice;

    private String promoPrice;

    private String subscriptionRealPrice;

    private String subscriptionPrice;

    private String subscriptionDescription;

    private String discountDescription;

    private boolean isPrivateEvent;

    private boolean isWorkshop;

    private boolean isExhibition;

    private int seatCountMax;

    private int seatsToShow;

    private int takenSeats;

}
