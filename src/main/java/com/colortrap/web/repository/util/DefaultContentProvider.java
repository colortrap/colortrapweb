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
        view.setStartAt(getDefaultNoContentText());
        view.setDatePicUrl(getCalendarPicURL(date.getDayOfMonth(), date.getMonthValue()));
        view.setPictureUrl(getDefaultEventPicUrl());
        view.setTitle(getDefaultDescription());
        view.setEventType(getDefaultNoContentText());
        view.setDescription(getDefaultNoContentText());
        view.setSuitableFor(getDefaultNoContentText());
        view.setTypePicUrl(getDefaultEventTypePicUrl());
        view.setPrice(getDefaultNoContentText());
        view.setDiscountedPrice(getDefaultNoContentText());
        
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
            case "Творческа лаборатория" -> url += "drawing" + ".png";
            case "Интуитивно рисуване и вино" -> url += "inspiration" + ".png";
            case "Вечер на тайни и предатели" -> url += "rpg" + ".png";
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

    public String getWorkshopDescription(String title, String eventType, int numberOrParagraph) {
        String description = "";
        String description1 = "";
        String description2 = "";
        String description3 = "Предишен опит не е необходим!";
        String description4 = "Ще се насладите на приятна атмосфера, спокойствие, чаша вино или друга напитка.\n";
        String description5 = "Резервирайте място сега и си подарете една творческа, спокойна и вдъхновяваща вечер.\n";
        String description6 = "Очакваме ви!";

        if (eventType.equals("Творческа лаборатория")){
            description = "Colortrap ви кани да творим заедно с усмивки и любопитство към новото.";
            description1 = "Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество.";
            description2 = "Нашият водещ ще ви напътства стъпка по стъпка как да създадете свое уникално творение, което да отнесете с гордост вкъщи или да подарите на любим човек.";
            description4 = "Ще се насладите на приятна атмосфера, усмивки, чаша сок или друга напитка.\n";
            description5 = "Резервирайте място сега и подарете на детето си цветно приключение, изпълнено с различни техники и материали.";

        }

        if (eventType.equals("Рисуване и вино") || eventType.equals("Рисуване на платно")){
        description = "Colortrap ви кани да нарисуваме заедно '" + title + "'.";
        description1 = "Всеки участник ще получи всички необходими материали на място и единствено е необходимо да дойдете с настроение за едно незабравимо изживяване.";
        description2 = "Нашият художник ще ви покаже как да нарисувате вашия собствен шедьовър, който да отнесете с гордост вкъщи или да подарите на любим човек.\n";
       }
        
        if (eventType.equals("Рисуване и вино за двама")){
            description = "Colortrap ви кани да дойдете двама и да нарисуваме заедно диптих '" + title + "'.";
            description1 = "Всеки участник ще получи всички необходими материали на място и единствено е необходимо да дойдете с настроение за едно незабравимо изживяване.";
            description2 = "Нашият художник ще ви покаже как да нарисувате вашия собствен шедьовър, който да отнесете с гордост вкъщи или да подарите на любим човек.\n";
        }

        if (eventType.equals("Галантерия") || eventType.equals("Галантерия и вино")){
            description = "Colortrap ви кани да ушием заедно кожен " + title + ". ";
            description1 = "Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество.";
            description2 = "Нашият водещ ще ви напътства стъпка по стъпка как да ушиете своя уникален кожен ключодържател, който да отнесете с гордост вкъщи или да подарите на любим човек.\n";
            
        }

        if (eventType.equals("Плюшена тел") || eventType.equals("Плюшена тел и вино")){
            if(title.startsWith("Рисуване с")){
                description = "Colortrap ви кани да рисуваме заедно с плюшена тел.";
                description1 = "Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество.";
                description2 = "Нашият водещ ще ви напътства стъпка по стъпка как да създадете своето уникалено торение от плюшена тел, което да отнесете с гордост вкъщи или да подарите на любим човек.";
                description4 = "По време на събитието ще се насладите на приятна атмосфера, усмивки, чаша сок или друга напитка.\n";
            } else {
            description = "Colortrap ви кани да сътворим заедно от плюшена тел " + title + ".";
            description1 = "Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество.";
            description2 = "Нашият водещ ще ви напътства стъпка по стъпка как да създадете своето уникалено торение от плюшена тел, което да отнесете с гордост вкъщи или да подарите на любим човек.";
                    
            }
        }
        
        if (eventType.equals("Полимерна глина") || eventType.equals("Полимерна глина и вино")){
            if(title.startsWith("Рисуване с")){
                description = "Colortrap ви кани да рисуваме заедно с полимерна глина.";
                description1 = "Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество.";
                description2 = "Нашият водещ ще ви напътства стъпка по стъпка как да създадете своята уникалена картина от полимерна глина, която да отнесете с гордост вкъщи или да подарите на любим човек.";
                description4 = "По време на събитието ще се насладите на приятна атмосфера, усмивки, чаша сок или друга напитка.\n";

            } else {
            description = "Colortrap ви кани да сътворим заедно от полимерна глина.";
            description1 = "Всеки участник ще получи всички необходими материали и инструменти на място и единствено е нужно да дойдете с добро настроение и желание за творчество.";
            description2 = "Нашият водещ ще ви напътства стъпка по стъпка как да създадете своето уникалено торение от полимерна глина, което да отнесете с гордост вкъщи или да подарите на любим човек.";
            
            }
        }
        if (eventType.equals("Интуитивно рисуване и вино")){
            description = "Colortrap ви кани на едно специално изживяване с интуитивно рисуване и чаша вино.\n";
            description1 = "Всеки участник ще получи всички необходими материали на място и единственото, което е нужно, е да дойдете с добро настроение и желание да се потопите в цветовете.\n";
            description2 = "Нашият водещ ще ви въведе в процеса и ще ви напътства, но творбата ще бъде изцяло ваше лично вдъхновение. Ще имате свободата да експериментирате, да играете с цветовете и да създадете своя уникален проект, който да отнесете вкъщи с гордост.\n";
            
        }
        if (eventType.equals("Вечер на тайни и предатели")){
            description = "Colortrap ви кани да се потопите в свят на тайни, блеф и неочаквани обрати.";
            description1 = "Всеки участник ще получи своя тайна роля още в началото на вечерта и единственото, което е нужно, е да дойдете с добро настроение, желание за игра и готовност да не вярвате на никого.";
            description2 = "Нашият водещ ще ви въведе в играта и ще ви напътства стъпка по стъпка, докато се опитвате да разкриете кой лъже… и кой е на ваша страна.";
            
            description4 = "Ще се насладите на динамична атмосфера, нови запознанства, много смях и напрежение… както и на включена напитка.";
            description5 = "Резервирайте своето място сега и се включете в едно различно социално преживяване, където всяка дума има значение.";
        }

        switch (numberOrParagraph) {
            case 1 : return description;
            case 2 : return description1;
            case 3 : return description2;
            case 4 : return description3;
            case 5 : return description4;
            case 6 : return description5;
            case 7 : return description6;
        
            default: return null;
        }
    }

    public String getPrivateEventDescription(String title, int numberOrParagraph) {
        String description = "";
        String description1 = "";
        String description2 = "По желание можем да предложим и кетъринг, за да добавим изискано обслужване и допълнителни удобства за вас и вашите гости. През цялото време на вашето събитие ще бъдем на ваше разположение на място, за да се уверим, че разполагате с всичко необходимо.";
        String description3 = "Резервирайте за желаната от вас дата поне 7 дни предварително, а ние ще се свържем с вас за допълнителна информация и потвърждаване на резервацията.";
        String description4 = "";

        if(title.startsWith("Работилница с")){
            description = "Ако търсите място за празнуване на рожден ден или друг детски празник – нашата частна арт работилница е идеалният избор.";
            description1 = "Не е нужно да мислите за украса, тъй като ние ще я осигурим за вас. Всички необходими материали за работилницата са включени, всяко дете ще получи напитка, а накрая ще вземе със себе си собственото си произведение.";
            
        } else if(title.startsWith("Организиране на събитие с продължителност")){
            description = "Ако търсите къде да проведете своето събитие – било то изложба, презентация, рожден ден или друг личен или фирмен празник – нашата зала е идеалният избор.";
            description1 = "Залата е 80 квадрата, разположени на две нива, с работилница до 25 седящи места, просторна и светла, осигурявайки комфорт и безпроблемно протичане на вашето събитие.";
        }

        switch (numberOrParagraph) {
            case 1 : return description;
            case 2 : return description1;
            case 3 : return description2;
            case 4 : return description3;
            case 5 : return description4;
        
            default: return null;
        }
    }

    public String getTitle(String title, String eventType) {
        if(eventType.startsWith("Рисуване")){
           return "'" + title + "'";
        }
        return title;
    }
    public String getSeatNotification(int maxCount, int taken, int toShow){
        if(maxCount == taken){
            return "Всички места са заети!";
        }
        if (toShow < taken){
            return "Поради големия интерес добавихме още места!";
        }
        return "";
    }
}
