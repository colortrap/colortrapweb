package com.colortrap.web.service.util;

import com.colortrap.web.model.entity.BaseWorkshop;
import com.colortrap.web.model.entity.ExhibitionEvent;
import com.colortrap.web.model.entity.PrivateEvent;
import com.colortrap.web.model.entity.WorkshopEvent;
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

        
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 1));
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 2));
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 3));
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 4));
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 5));
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 6));
        view.setDescription(contentProvider.getWorkshopDescription(workshop.getTitle(), workshop.getEventType(), 7));

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
            } else view.setDiscountDescription("");
        } else {
            view.setPromoPrice("");
        }

        if(!(workshop.isDiscounted() || workshop.isPromo())){
            view.setDiscountDescription("");
        }

        if(workshop.isSubscription()){
            view.setSubscriptionPrice(workshop.getDiscount().getSubscriptionPrice());
            if (workshop.getDiscount().getSubscriptionDescription() != null){
                view.setSubscriptionDescription(workshop.getDiscount().getSubscriptionDescription());
            } else view.setSubscriptionDescription("");
        } else {
            view.setSubscriptionPrice("");
        }

        return view;
    }

    public WorkshopView mapExhibitionEntityToView (ExhibitionEvent workshop){
        WorkshopView view = new WorkshopView();
        view.setId(workshop.getId());
        view.setPictureUrl(workshop.getPictureUrl());
        view.setTitle(workshop.getTitle());
        view.setEventType(workshop.getEventType());
        LocalDate today = LocalDate.now();
        view.setDatePicUrl(contentProvider.getCalendarPicURL(today.getDayOfMonth(), today.getMonthValue()));
        view.setDateEndPicUrl(contentProvider.getCalendarPicURL(workshop.getEventDate().getEndDay(), workshop.getEventDate().getEndMonth()));
        view.setTypePicUrl(contentProvider.getTypePicUrl(workshop.getEventType()));
        view.setStartAt(workshop.getStartAt());

        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getEventType(), 1));
        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getEventType(), 2));
        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getEventType(), 3));
        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getEventType(), 4));
        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getEventType(), 5));
        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getEventType(), 6));
        view.setDescription(contentProvider.getPrivateEventDescription(workshop.getEventType(), 7));

        view.setPrice(workshop.getPrice());

        return view;
    }

        public WorkshopView mapPrivateEventEntityToView (PrivateEvent workshop){
        WorkshopView view = new WorkshopView();
        view.setId(workshop.getId());
        view.setPictureUrl(workshop.getPictureUrl());
        view.setTitle(workshop.getTitle());
        view.setEventType(workshop.getEventType());

        view.setDescription(workshop.getDescription());

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

        return view;
    }
}
