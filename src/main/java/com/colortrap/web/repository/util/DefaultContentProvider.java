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
        return "Няма намерено събитие.";
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
        view.setPrice2(getDefaultNoContentText());
        view.setPrice5(getDefaultNoContentText());
        
        return view;
    }

    public String getTypePicUrl(String eventType) {
        String url = "pictures/eventType/";

        switch (eventType) {
            case "Рисуване на платно" -> url += "paint-palette" + ".png";
            case "Рисуване и вино" -> url += "wine" + ".png";
            case "Рисуване и вино за двама" -> url += "for2" + ".png";
            case "Изложба" -> url += "gallery" + ".png";
            case "Работилница" -> url += "inspiration" + ".png";
            case "Галантерия" -> url += "leather" + ".png";
            case "Галантерия и вино" -> url += "wine" + ".png";
            case "Плюшена тел" -> url += "feather-duster" + ".png";
            case "Плюшена тел и вино" -> url += "wine" + ".png";
            case "Полимерна глина" -> url += "pottery" + ".png";
            case "Рисуване на дърво" -> url += "paint" + ".png"; 
            case "Интуитивно рисуване и вино" -> url += "inspiration" + ".png";      
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

    public String getPictureUrlByType(String eventType) {
        return "works/" + eventType + ".png";
    }

    public String getPictureUrlByTitle(String title) {
        return "works/" + title + ".jpg";
    }

    public String getDescription(String title, String eventType) {
        String description = "";    
        if (eventType.equals("Рисуване и вино") || eventType.equals("Рисуване на платно")){
        description = "Colortrap ви кани да нарисуваме заедно '" + title + 
        "'. Всеки участник ще получи всички необходими материали на място и единствено е необходимо да дойдете с настроение за едно незабравимо изживяване. Нашият художник ще ви покаже как да нарисувате вашия собствен шедьовър, който да отнесете с гордост вкъщи или да подарите на любим човек.\n"+  //
       "Предишен опит не е необходим!\n" + //
                                "Резервирайте своето място сега и си подарете едно вдъхновяващо и уютно арт изживяване. Очакваме ви!";         
        }
        
        if (eventType.equals("Рисуване и вино за двама")){
            description = "Colortrap ви кани да дойдете двама и да нарисуваме заедно диптих '" + title + 
        "'. Всеки участник ще получи всички необходими материали на място и единствено е необходимо да дойдете с настроение за едно незабравимо изживяване. Нашият художник ще ви покаже как да нарисувате вашия собствен шедьовър, който да отнесете с гордост вкъщи или да подарите на любим човек.\n"+  //
        "Предишен опит не е необходим!\n" + //
           "Резервирайте своето място сега и си подарете едно вдъхновяващо и уютно арт изживяване. Очакваме ви!";
        }

        if (eventType.equals("Галантерия") || eventType.equals("Галантерия и вино")){
            description = "Colortrap ви кани да ушием заедно кожен " + title +
            ". Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество. Нашият водещ ще ви напътства стъпка по стъпка как да ушиете своя уникален кожен ключодържател, който да отнесете с гордост у дома или да подарите на любим човек.\n" + //
                                "Предишен опит не е необходим!\n" + //
                                "Резервирайте своето място сега и си подарете едно вдъхновяващо и уютно арт изживяване. Очакваме ви!";
        }

        if (eventType.equals("Плюшена тел") || eventType.equals("Плюшена тел и вино")){
            if(title.contains("Рисуване с")){
                description = "Colortrap ви кани да рисуваме заедно с плюшена тел" +
            ". Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество. Нашият водещ ще ви напътства стъпка по стъпка как да създадете своето уникалено вторение от плюшена тел, което да отнесете с гордост у дома или да подарите на любим човек.\n" + //
                                "Предишен опит не е необходим!\n" + //
                                "Резервирайте своето място сега и си подарете едно вдъхновяващо и уютно арт изживяване. Очакваме ви!";
            } else {
            description = "Colortrap ви кани да сътворим заедно от плюшена тел " + title +
            ". Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество. Нашият водещ ще ви напътства стъпка по стъпка как да създадете своето уникалено вторение от плюшена тел, което да отнесете с гордост у дома или да подарите на любим човек.\n" + //
                                "Предишен опит не е необходим!\n" + //
                                "Резервирайте своето място сега и си подарете едно вдъхновяващо и уютно арт изживяване. Очакваме ви!";
        
            }
        }
        
        if (eventType.equals("Полимерна глина") || eventType.equals("Полимерна глина и вино")){
            if(title.contains("Рисуване с")){
                description = "Colortrap ви кани да рисуваме заедно с полимерна глина" +
            ". Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество. Нашият водещ ще ви напътства стъпка по стъпка как да създадете своето уникалено вторение от плюшена тел, което да отнесете с гордост у дома или да подарите на любим човек.\n" + //
                                "Предишен опит не е необходим!\n" + //
                                "Резервирайте своето място сега и си подарете едно вдъхновяващо и уютно арт изживяване. Очакваме ви!";
            } else {
            description = "Colortrap ви кани да сътворим заедно от полимерна глина " + title +
            ". Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество. Нашият водещ ще ви напътства стъпка по стъпка как да създадете своето уникалено вторение от плюшена тел, което да отнесете с гордост у дома или да подарите на любим човек.\n" + //
                                "Предишен опит не е необходим!\n" + //
                                "Резервирайте своето място сега и си подарете едно вдъхновяващо и уютно арт изживяване. Очакваме ви!";
        
            }
        }
        if (eventType.equals("Интуитивно рисуване и вино")){
            description = "Colortrap ви кани на едно специално изживяване с интуитивно рисуване и чаша вино.\n" + //
                        "Всеки участник ще получи всички необходими материали на място и единственото, което е нужно, е да дойдете с добро настроение и желание да се потопите в цветовете.\n" + //
                        "\n" + //
                        "Нашият водещ ще ви въведе в процеса и ще ви напътства, но творбата ще бъде изцяло ваше лично вдъхновение. Ще имате свободата да експериментирате, да играете с цветовете и да създадете свой уникален проект, който да отнесете у дома с гордост.\n" + //
                        "\n" + //
                        "Предишен опит не е необходим!\n" + //
                        "По време на събитието ще се насладите на приятна атмосфера, релакс и чаша вино.\n" + //
                        "\n" + //
                        "Резервирайте своето място сега и си подарете една творческа, спокойна и вдъхновяваща вечер.\n" + //
                        "Очакваме ви!";
        }

        return description;
    }

    public String getTitle(String title, String eventType) {
        if(eventType.contains("Рисуване")){
           return "'" + title + "'";
        }
        return title;
    }
}
