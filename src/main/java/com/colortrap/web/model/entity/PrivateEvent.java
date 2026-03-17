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
        return discount != null && (discount.getPrice5() == null && discount.getPrice2() == null);
    }

    public boolean isPromo(){
        return discount !=null && discount.getPromoPrice() != null;
    }

}
