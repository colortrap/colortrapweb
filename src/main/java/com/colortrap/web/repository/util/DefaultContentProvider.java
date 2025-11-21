package com.colortrap.web.repository.util;

import com.colortrap.web.model.enumeration.SuitableForEnum;
import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.service.util.DateProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DefaultContentProvider {
    final private DateProvider dateProvider;

    public DefaultContentProvider(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    public String getDefaultEventPicUrl(){
        return "pictures/decor/pictureNotFound_image.png";
    }

    public String getDefaultEventTypePicUrl(){
        return "pictures/eventType/rejected.png";
    }

    public String getDefaultDescription(){
        return "Няма арт вечери за деня.";
    }

    public String getDefaultNoContentText(){
        return "-";
    }

    public String getCalendarPicURL(int day, int month){
        return "pictures/calendar/" + day + "-" + month + ".png";
    }

    public WorkshopView getDefaultWorkshopVew (String id){
        WorkshopView view = new WorkshopView();
        LocalDate date = dateProvider.getDate();

        view.setId(id);
        view.setDay(date.getDayOfMonth());
        view.setMonth(date.getMonthValue());
        view.setYear(date.getYear());
        view.setStartAt(getDefaultNoContentText());
        view.setDatePicUrl(getCalendarPicURL(date.getDayOfMonth(), date.getMonthValue()));
        view.setPictureUrl(getDefaultEventPicUrl());
        view.setTitle(getDefaultDescription());
        view.setEventType(getDefaultNoContentText());
        view.setDescription(getDefaultNoContentText());
        view.setSuitableFor(getDefaultNoContentText());
        view.setTypePicUrl(getDefaultEventTypePicUrl());
        view.setPrice(getDefaultNoContentText());
        
        return view;
    }

    public String getTypePicUrl(String eventType) {
        String url = "pictures/eventType/";

        switch (eventType) {
            case "Рожден ден" -> url += "birthday-cake" + ".png";
            case "Моминско парти" -> url += "marriage" + ".png";
            case "Арт занимание за деца" -> url += "drawing" + ".png";
            case "Рисуване и вино" -> url += "paint" + ".png";
            case "Изложба" -> url += "red-carpet" + ".png";
            case "Тимбилдинг" -> url += "lecture" + ".png";
            case "Работилница" -> url += "inspiration" + ".png";
            default -> url += "rejected" + ".png";
        }

        return url;
    }

    public String getSuitableForString(SuitableForEnum suitableForEnum){
        if(suitableForEnum == null){
            suitableForEnum = SuitableForEnum.UNKNOWN;
        }
        String suitableForName = suitableForEnum.name();
        String suitableForString = "";
        switch (suitableForName ) {
            case "CHILDREN": suitableForString = "За деца"; break;
            case "ADULTS": suitableForString = "За възрастни"; break;
            default: suitableForString = "За всички"; break;
        }
        return suitableForString;
    }

    public String getPictureUrlByType(String title) {
        return "works/" + title + ".jpg";
    }

    public String getDescription(String title, String eventType) {
        return "Colortrap ви кани да нарисуваме заедно '" + title + "'. Всеки посетител ще получи всички необходими материали на място и единствено е необходимо да дойдете с настроение за едно незабравимо изживяване. Нашият художник ще Ви покаже как да нарисувате Вашия собствен шедьовър, който да отнесете с гордост вкъщи. Опит не е необходим! Запишете се и резервирайте своето място сега. Очакваме Ви!";
    }
}
