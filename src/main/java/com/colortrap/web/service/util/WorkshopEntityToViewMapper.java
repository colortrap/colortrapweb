package com.colortrap.web.service.util;

import com.colortrap.web.model.entity.BaseWorkshop;
import com.colortrap.web.model.entity.ExhibitionEvent;
import com.colortrap.web.model.entity.PrivateEvent;
import com.colortrap.web.model.entity.WorkshopEvent;
import com.colortrap.web.model.enumeration.SuitableForEnum;
import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.repository.util.DefaultContentProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkshopEntityToViewMapper {
    final private DefaultContentProvider contentProvider;

    public WorkshopEntityToViewMapper(DefaultContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }


    public List<WorkshopView> mapWorkshopEntityListToView(List<WorkshopEvent> all) {
        List<WorkshopView> viewList = new ArrayList<>();
        if (!all.isEmpty()) {
            for (BaseWorkshop workshop : all) {
                if(workshop.isWorkshop()){            
                    viewList.add(mapWorkshopEntityToView((WorkshopEvent) workshop));
                }
            }
        }
        return viewList;
    }

    public List<WorkshopView> mapPrivateEventListToView(List<PrivateEvent> all) {
        List<WorkshopView> viewList = new ArrayList<>();
        if (!all.isEmpty()) {
            for (BaseWorkshop workshop : all) {   
                if(workshop.isPrivateEvent()){           
                    viewList.add(mapPrivateEventEntityToView((PrivateEvent) workshop));
                }
            }
        }
        return viewList;
    }

    public List<WorkshopView> mapExhibitionEntityToViewList(List<ExhibitionEvent> all) {
        List<WorkshopView> viewList = new ArrayList<>();
        if (!all.isEmpty()) {
            for (BaseWorkshop workshop : all) {   
                if(workshop.isExhibition()){           
                    viewList.add(mapExhibitionEntityToView((ExhibitionEvent) workshop));
                }
            }
        }
        return viewList;
    }

    public WorkshopView mapWorkshopEntityToView (WorkshopEvent workshop){
        WorkshopView view = new WorkshopView();
        view.setId(workshop.getId());
        view.setPictureUrl(workshop.getPictureUrl());
        view.setTitle(workshop.getTitle());
        view.setEventType(workshop.getEventType());
        view.setDatePicUrl(contentProvider.getCalendarPicURL(workshop.getEventDate().getStartDay(), workshop.getEventDate().getStartMonth()));
        view.setTypePicUrl(contentProvider.getTypePicUrl(workshop.getEventType()));
        view.setSuitableFor(contentProvider.getSuitableForString(workshop.getSuitableFor()));
        view.setStartAt(workshop.getStartAt());
        view.setEndAt(workshop.getEndAt());

        
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 1));
        view.setDescription1(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 2));
        view.setDescription2(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 3));
        view.setDescription3(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 4));
        view.setDescription4(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 5));
        view.setDescription5(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 6));
        view.setDescription6(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 7));

        view.setPrice(workshop.getPrice());
        if(workshop.isDiscounted()){
            view.setDiscountedPrice(workshop.getDiscount().getDiscountedPrice());
            if (workshop.getDiscount().getDiscountDescription() != null){
                view.setDiscountDescription(workshop.getDiscount().getDiscountDescription());
            } else view.setDiscountDescription("");
        } else {
            view.setDiscountedPrice("");
        }
                
        if(workshop.isPromo()){
            view.setPromoPrice(workshop.getDiscount().getPromoPrice());
            if (workshop.getDiscount().getDiscountDescription() != null){
                view.setDiscountDescription(workshop.getDiscount().getDiscountDescription());
                view.setPrice(view.getPromoPrice());
                if(view.getPromoPrice().isEmpty() || view.getPromoPrice() == null){
                view.setDiscountedPrice(view.getPromoPrice());
            } else {
                view.setDiscountedPrice(view.getDiscountedPrice());
                }
            } else view.setDiscountDescription("");
        } else {
            view.setPromoPrice("");
        }

        if(!(workshop.isDiscounted() || workshop.isPromo())){
            view.setDiscountDescription("");
        }

        if(workshop.isSubscription()){
            view.setSubscriptionPrice(workshop.getDiscount().getSubscriptionPrice());
            view.setSubscriptionRealPrice(workshop.getDiscount().getSubscriptionRealPrice());
            if (workshop.getDiscount().getSubscriptionDescription() != null){
                view.setSubscriptionDescription(workshop.getDiscount().getSubscriptionDescription());
            } else view.setSubscriptionDescription("");
        } else {
            view.setSubscriptionPrice("");
            view.setSubscriptionRealPrice("");
            view.setSubscriptionDescription("");
        }

        view.setSeatsToShow(workshop.getFreeSeatsToShow());
        view.setSeatNotification(contentProvider.getSeatNotification(workshop.getSeats().getSeatsCountMax(), workshop.getSeats().getTakenSeats(), workshop.getSeats().getSeatsToShow()));

        return view;
    }

    public WorkshopView mapExhibitionEntityToView (ExhibitionEvent workshop){
        WorkshopView view = new WorkshopView();
        view.setId(workshop.getId());
        view.setPictureUrl(workshop.getPictureUrl());
        view.setTitle(workshop.getTitle());
        view.setEventType(workshop.getEventType());
        LocalDate today = LocalDate.now();
        LocalDate starDate = LocalDate.of(workshop.getEventDate().getStartYear(), workshop.getEventDate().getStartMonth(), workshop.getEventDate().getStartDay());

        if(starDate.isBefore(today)){
            view.setDatePicUrl(contentProvider.getCalendarPicURL(today.getDayOfMonth(), today.getMonthValue()));
        } else {
            view.setDatePicUrl(contentProvider.getCalendarPicURL(starDate.getDayOfMonth(), starDate.getMonthValue()));
        }
        view.setDateEndPicUrl(contentProvider.getCalendarPicURL(workshop.getEventDate().getEndDay(), workshop.getEventDate().getEndMonth()));
        view.setTypePicUrl(contentProvider.getTypePicUrl(workshop.getEventType()));
        view.setStartAt(workshop.getStartAt());

        view.setDescription(workshop.getDescription());        
        view.setSuitableFor(contentProvider.getSuitableForString(SuitableForEnum.ALL));

        view.setPrice(workshop.getPrice());

        return view;
    }

        public WorkshopView mapPrivateEventEntityToView (PrivateEvent workshop){
        WorkshopView view = new WorkshopView();
        view.setId(workshop.getId());
        view.setPictureUrl(workshop.getPictureUrl());
        view.setTitle(workshop.getTitle());
        view.setEventType(workshop.getEventType());        

        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getTitle(), 1));
        view.setDescription1(contentProvider.getPrivateEventDescription(workshop.getTitle(), 2));
        view.setDescription2(contentProvider.getPrivateEventDescription(workshop.getTitle(), 3));
        view.setDescription3(contentProvider.getPrivateEventDescription(workshop.getTitle(), 4));
        view.setDescription4(contentProvider.getPrivateEventDescription(workshop.getTitle(), 5));
        view.setDescription5(contentProvider.getPrivateEventDescription(workshop.getTitle(), 6));
        view.setDescription6(contentProvider.getPrivateEventDescription(workshop.getTitle(), 7));

        view.setPrice(workshop.getPrice());
        if(workshop.isDiscounted()){
            if(workshop.isDiscounted()){
                view.setDiscountedPrice(workshop.getDiscount().getDiscountedPrice());
                if (workshop.getDiscount().getDiscountDescription() != null){
                    view.setDiscountDescription(workshop.getDiscount().getDiscountDescription());
                } else view.setDiscountDescription("");
            } else {
                view.setDiscountedPrice("");
            }
        }
        
        if(workshop.isPromo()){
            view.setPromoPrice(workshop.getDiscount().getPromoPrice());
            if (workshop.getDiscount().getDiscountDescription() != null){
                view.setDiscountDescription(workshop.getDiscount().getDiscountDescription());
            } else view.setDiscountDescription("");
        } else {
            view.setPromoPrice("");
        }

        if(!(workshop.isDiscounted() || workshop.isPromo())){
            view.setDiscountDescription("");
        }
        
        view.setSubscriptionPrice("");
        view.setSubscriptionDescription("");
        view.setSuitableFor(contentProvider.getSuitableForString(SuitableForEnum.ALL));

        return view;
    }
}
