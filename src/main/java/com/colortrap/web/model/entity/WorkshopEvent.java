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

    private Seats seats;
    
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

    public int getFreeSeatsToShow(){
        int freeSeats = seats.getSeatsCountMax() - seats.getTakenSeats();
        if(seats.getSeatsToShow() < 4 && seats.getSeatsToShow() < freeSeats){
            if(freeSeats > 4){
                seats.setSeatsToShow(seats.getSeatsToShow() + 4);
            } else{
                seats.setSeatsToShow(seats.getSeatsToShow() + freeSeats);
            }
        }
        return seats.getSeatsToShow();
    }

    public boolean doSeatReservation(String count){
        int number;
        try{
            number = Integer.parseInt(count);

            if(seats.getSeatsToShow() >= number){
                seats.setTakenSeats(seats.getTakenSeats() + number); 
                return true;
            }
        } catch (Exception e){
            return false;
        }
        return false;

    }

}
