package com.colortrap.web.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Discount {
    
    private String price2;

    private String price5;

    private String promoPrice;

    private String subscriptionPrice;

    private String discountDescription;

    private String subscriptionDescription;

    public void updateDiscount(boolean isDiscounted){
        if(!isDiscounted){
            setPrice2(null);
            setPrice5(null);
            setPromoPrice(null);
            setSubscriptionPrice(null);
        }
    }
}
