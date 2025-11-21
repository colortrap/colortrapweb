package com.colortrap.web.model.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class WorkshopView{
    private String id;

    private Integer day;

    private Integer month;

    private Integer year;

    private String datePicUrl;

    private String startAt;

    private String suitableFor;

    private String eventType;

    private String typePicUrl;

    private String pictureUrl;

    private String title;

    private String description;

    private String price;

    private boolean isActive;

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
