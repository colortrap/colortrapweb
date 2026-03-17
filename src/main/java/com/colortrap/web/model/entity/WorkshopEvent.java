package com.colortrap.web.model.entity;

import com.colortrap.web.model.enumeration.SuitableForEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkshopEvent extends BaseWorkshop{

    private EventDate eventDate;
    
    private String startAt;

    private String endAt;
        
    private SuitableForEnum suitableFor;

    private String price;

    private Discount discount;
    
    public boolean isDiscounted(){
        return discount != null && (discount.getPrice5().isEmpty() && discount.getPrice2().isEmpty());
    }

    public boolean isPromo(){
        return discount !=null && discount.getPromoPrice().isEmpty();
    }

    public boolean isSubscription(){
        return discount != null && discount.getSubscriptionPrice().isEmpty();
    }

}
