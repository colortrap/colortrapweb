package com.colortrap.web.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BaseWorkshop{    

    private String id;
    
    private String pictureUrl;

    private String title;    

    private String eventType;
    
    private boolean isActive;

    private boolean isPrivateEvent;

    private boolean isWorkshop;

    private boolean isExhibition;
}
