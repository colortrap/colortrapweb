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

    private String price2;

    private String price5;

    private String promoPrice;

    private String subscriptionPrice;

    private String subscriptionDescription;

    private String discountDescription;



    private boolean isActive;

    private boolean isPrivateEvent;

    private boolean isPrivateWorkshop;

    private boolean isWorkshop;

    private boolean isExhibition;

}
