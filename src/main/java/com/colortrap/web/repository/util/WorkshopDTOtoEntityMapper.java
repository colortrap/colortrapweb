package com.colortrap.web.repository.util;

import org.springframework.stereotype.Component;

import com.colortrap.web.model.dto.WorkshopDTO;
import com.colortrap.web.model.entity.BaseWorkshop;
import com.colortrap.web.model.entity.Discount;
import com.colortrap.web.model.entity.EventDate;
import com.colortrap.web.model.entity.ExhibitionEvent;
import com.colortrap.web.model.entity.PrivateEvent;
import com.colortrap.web.model.entity.WorkshopEvent;

@Component
public class WorkshopDTOtoEntityMapper {
final private DefaultContentProvider contentProvider;

public WorkshopDTOtoEntityMapper(DefaultContentProvider contentProvider){
    this.contentProvider = contentProvider;
}

public BaseWorkshop mapToEntity(WorkshopDTO workshopDTO) {
    if(workshopDTO.isWorkshop()){
        WorkshopEvent workshopEvent = new WorkshopEvent();
        baseMapper(workshopDTO, workshopEvent);

        EventDate eventDate = new EventDate();
        eventDate.setStartDay(workshopDTO.getStartDay());
        eventDate.setStartMonth(workshopDTO.getStartMonth());
        eventDate.setStartYear(workshopDTO.getStartYear());
        eventDate.setEndDay(workshopDTO.getStartDay());
        eventDate.setEndMonth(workshopDTO.getStartMonth());
        eventDate.setEndYear(workshopDTO.getStartYear());

        workshopEvent.setEventDate(eventDate);
        workshopEvent.setId("" + workshopEvent.getEventDate().getStartDay() + "-" + workshopEvent.getEventDate().getStartMonth() + "-"  + workshopEvent.getEventDate().getStartYear() + "-" );
        
        workshopEvent.setStartAt(workshopDTO.getStartAt());
        workshopEvent.setEndAt(workshopDTO.getEndAt());
        workshopEvent.setSuitableFor(workshopDTO.getSuitableFor());
        workshopEvent.setPrice(workshopDTO.getPrice());

        Discount discount = new Discount();
        discount.setDiscountedPrice(workshopDTO.getDiscountedPrice());
        discount.setPromoPrice(workshopDTO.getPromoPrice());
        discount.setSubscriptionDescription(workshopDTO.getSubscriptionDescription());
        discount.setSubscriptionPrice(workshopDTO.getSubscriptionPrice());
        discount.setSubscriptionRealPrice(workshopDTO.getSubscriptionRealPrice());
        discount.setDiscountDescription(workshopDTO.getDiscountDescription());

        workshopEvent.setDiscount(discount);

        workshopEvent.setActive(true);

        return workshopEvent;

    }else if (workshopDTO.isExhibition()){        
        ExhibitionEvent exhibitionEvent = new ExhibitionEvent();
        baseMapper(workshopDTO, exhibitionEvent);

        EventDate eventDate = new EventDate();
        eventDate.setStartDay(workshopDTO.getStartDay());
        eventDate.setStartMonth(workshopDTO.getStartMonth());
        eventDate.setStartYear(workshopDTO.getStartYear());
        eventDate.setEndDay(workshopDTO.getEndDay());
        eventDate.setEndMonth(workshopDTO.getEndMonth());
        eventDate.setEndYear(workshopDTO.getEndYear());

        exhibitionEvent.setEventDate(eventDate);
        exhibitionEvent.setId("" + exhibitionEvent.getEventDate().getStartDay() + "-" + exhibitionEvent.getEventDate().getStartMonth() + "-" + exhibitionEvent.getEventDate().getStartYear() + "-" );

        exhibitionEvent.setDescription(workshopDTO.getDescription());
        exhibitionEvent.setStartAt(workshopDTO.getStartAt());
        exhibitionEvent.setPrice(workshopDTO.getPrice());

        exhibitionEvent.setActive(true);

        return exhibitionEvent;
    }else if (workshopDTO.isPrivateEvent()){
        PrivateEvent privateEvent = new PrivateEvent();
        baseMapper(workshopDTO, privateEvent);
        
        privateEvent.setPrice(workshopDTO.getPrice());
        privateEvent.setId("" + 0 + "-" + 0 + "-" + 0 + "-" );

        Discount discount = new Discount();
        discount.setDiscountedPrice(workshopDTO.getDiscountedPrice());
        discount.setPromoPrice(workshopDTO.getPromoPrice());
        discount.setSubscriptionDescription(workshopDTO.getSubscriptionDescription());
        discount.setSubscriptionPrice(workshopDTO.getSubscriptionPrice());
        discount.setDiscountDescription(workshopDTO.getDiscountDescription());

        privateEvent.setDiscount(discount);
        privateEvent.setPrivateWorkshop(workshopDTO.getTitle().startsWith("Работилница с"));

        privateEvent.setActive(true);
        
        return privateEvent;
    }
    
    return null;
}

private BaseWorkshop baseMapper(WorkshopDTO workshopDTO,BaseWorkshop workshop){
    workshop.setActive(true);
    workshop.setExhibition(workshopDTO.isExhibition());
    workshop.setPrivateEvent(workshopDTO.isPrivateEvent());
    workshop.setWorkshop(workshopDTO.isWorkshop());

    workshop.setEventType(workshopDTO.getEventType());
    workshop.setTitle(contentProvider.getTitle(workshopDTO.getTitle(), workshopDTO.getEventType()));
    workshop.setPictureUrl(contentProvider.getPictureUrlByTitle(workshopDTO.getTitle()));

    if(workshop.getEventType().equals("Интуитивно рисуване и вино") || workshop.getEventType().equals("Творческа лаборатория") || workshopDTO.isPrivateEvent()){
        workshop.setPictureUrl(contentProvider.getPictureUrlByType(workshopDTO.getEventType()));
    }
 
    return workshop;
}


}
