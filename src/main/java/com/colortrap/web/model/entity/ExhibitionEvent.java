package com.colortrap.web.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExhibitionEvent extends BaseWorkshop{
    private EventDate eventDate;

    private String startAt;
    
    private String price;

    private String description;

}
