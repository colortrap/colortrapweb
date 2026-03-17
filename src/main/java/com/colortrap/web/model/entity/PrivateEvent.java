package com.colortrap.web.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PrivateEvent extends BaseWorkshop {
    
    private String price;

    private Discount discount;

    private boolean isPrivateWorkshop;

    private String description;
    
    public boolean isDiscounted(){
        if(discount != null || discount.getDiscountedPrice() == null){
            return false;
        } else if (discount.getDiscountedPrice().isEmpty()){
            return false;
        }
        return true;
    }

    public boolean isPromo(){
        if(discount != null || discount.getPromoPrice() == null){
            return false;
        } else if (discount.getPromoPrice().isEmpty()){
            return false;
        }
        return true;
    }

}
