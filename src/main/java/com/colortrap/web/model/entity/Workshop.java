package com.colortrap.web.model.entity;

import com.colortrap.web.model.enumeration.SuitableForEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class Workshop extends  BaseEntity{

    private int day;

    private int month;

    private int year;
    
    private String startAt;
    
    private SuitableForEnum suitableFor;

    private String eventType;

    private String pictureUrl;

    private String title;
    
    private String description;
    
    private boolean isActive;

    private String price;

    private String price2;

    private String price5;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
}
