package com.colortrap.web.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Discount {
    
    private String discountedPrice;

    private String promoPrice;

    private String subscriptionPrice;

    private String discountDescription;

    private String subscriptionDescription;
}
