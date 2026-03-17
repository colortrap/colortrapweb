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
        if(discount == null || discount.getDiscountedPrice() == null){
            return false;
        } else if (discount.getDiscountedPrice().isEmpty() || discount.getDiscountedPrice().isBlank()){
            discount.setDiscountedPrice(null);
            return false;
        }
        return true;
    }

    public boolean isPromo(){
        if(discount == null || discount.getPromoPrice() == null){
            return false;
        } else if (discount.getPromoPrice().isEmpty()){
            discount.setPromoPrice(null);
            return false;
        }
        return true;
    }

    public boolean isSubscription(){
        if(discount == null || discount.getSubscriptionPrice() == null){
            return false;
        } else if (discount.getSubscriptionPrice().isEmpty()){
            discount.setSubscriptionPrice(null);
            return false;
        }
        return true;
    }

}
