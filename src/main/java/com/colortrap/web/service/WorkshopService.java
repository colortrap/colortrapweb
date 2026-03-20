package com.colortrap.web.service; 

import com.colortrap.web.model.entity.BaseWorkshop;
import com.colortrap.web.model.entity.ExhibitionEvent;
import com.colortrap.web.model.entity.PrivateEvent;
import com.colortrap.web.model.entity.WorkshopEvent;
import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.repository.WorkshopRepo;
import com.colortrap.web.repository.util.DefaultContentProvider;
import com.colortrap.web.service.util.DateProvider;
import com.colortrap.web.service.util.WorkshopEntityToViewMapper;
import com.colortrap.web.web.error.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkshopService {
    final private WorkshopRepo workshopRepo;
    final private DateProvider dateProvider;
    final private WorkshopEntityToViewMapper workshopMapper;
    final private DefaultContentProvider contentProvider;
    private LocalDate upToDate;

    public WorkshopService(WorkshopRepo workshopRepo, DateProvider dateProvider,
                           WorkshopEntityToViewMapper workshopMapper, DefaultContentProvider contentProvider){
        this.workshopRepo = workshopRepo;
        this.dateProvider = dateProvider;
        this.workshopMapper = workshopMapper;
        this.contentProvider = contentProvider;
        this.upToDate = dateProvider.getDate();
    }

    public List<WorkshopView> getWorkshopsForCalendar(){
        checkIsActiveWorkshopsUpToNow();
        List<WorkshopView> views = workshopMapper.mapWorkshopEntityListToView(sortWorkshopsByDate(getActiveWorkshops()));
        if (views.isEmpty()){
            views.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }
        for (int i = 0; i < views.size(); i++) {
            if(views.get(i).getPromoPrice() != null && !views.get(i).getPromoPrice().isEmpty()){
                views.get(i).setPrice(views.get(i).getPromoPrice());
            }
        }
        while (views.size() > 41){
            views.remove(41);
        }
        return views;
    }
    
    public List<WorkshopView> getAllForGalleryCalendar(){
        List<WorkshopView> views = workshopMapper.mapExhibitionEntityToViewList(sortExhibitionsByDate(getActiveExhibitions()));
        while (views.size() > 41){
            views.remove(41);
        }
        return views;
    }

    public List<WorkshopView> getWorkshopsForCalendarDiscount(){
        checkIsActiveWorkshopsUpToNow();
        List<WorkshopView> views = workshopMapper.mapWorkshopEntityListToView(sortWorkshopsByDate(getDiscountedWorkshops()));
        while (views.size() > 41){
            views.remove(41);
        }
        return views;
    }
    
    public List<WorkshopView> getWorkshopsForCalendarSubscription(){
        checkIsActiveWorkshopsUpToNow();
        List<WorkshopView> views = workshopMapper.mapWorkshopEntityListToView(sortWorkshopsByDate(getSubscriptionWorkshops()));
        while (views.size() > 41){
            views.remove(41);
        }
        return views;
    }
    
    public List<WorkshopView> getWorkshopsForCalendarPromo(){
        checkIsActiveWorkshopsUpToNow();
        List<WorkshopView> views = workshopMapper.mapWorkshopEntityListToView(sortWorkshopsByDate(getPromoWorkshops()));
        for (int i = 0; i < views.size(); i++) {
            views.get(i).setPrice(views.get(i).getPromoPrice());
        }
        while (views.size() > 41){
            views.remove(41);
        }
        return views;
    }
    
        public List<WorkshopView> getDiscountForShare(){
        List<WorkshopView> views = getWorkshopsForCalendarDiscount();
        while (views.size() > 3){
            views.remove(3);
        }
        return views;
    }
    
    public List<WorkshopView> getPromosForShare(){
        List<WorkshopView> views = getWorkshopsForCalendarPromo();
        while (views.size() > 3){
            views.remove(3);
        }
        return views;
    }

    public List<WorkshopView> getSubscriptionForShare(){
        List<WorkshopView> views = getWorkshopsForCalendarSubscription();
        while (views.size() > 3){
            views.remove(3);
        }
        return views;
    }

    public List<WorkshopView> getWorkshopsForIndex(){
        List<WorkshopEvent> workshops = sortWorkshopsByDate(getActiveWorkshops());
        List<ExhibitionEvent> exhibitionEvents = getActiveExhibitions();
        List<WorkshopView> views = new ArrayList<>();

        if(!exhibitionEvents.isEmpty()){
            LocalDate date = dateProvider.getDate();
            for (ExhibitionEvent event : exhibitionEvents) {
                LocalDate startDate = LocalDate.of(event.getEventDate().getStartYear(), event.getEventDate().getStartMonth(), event.getEventDate().getStartDay());
                LocalDate enDate = LocalDate.of(event.getEventDate().getEndYear(), event.getEventDate().getEndMonth(), event.getEventDate().getEndDay());
                if ((date.isBefore(enDate) || date.isEqual(enDate)) && (date.isAfter(startDate) || date.isEqual(startDate))){
                    views.add(workshopMapper.mapExhibitionEntityToView(event));
                        break;
                }
            }
        }

        if(!workshops.isEmpty()){
            LocalDate date = dateProvider.getDate();
            for (WorkshopEvent workshop : workshops) {
                LocalDate enDate = LocalDate.of(workshop.getEventDate().getStartYear(), workshop.getEventDate().getStartMonth(), workshop.getEventDate().getStartDay());
                if (date.isEqual(enDate)){
                    views.add(workshopMapper.mapWorkshopEntityToView(workshop));
                    if (views.size() >= 3){
                        break;
                    }
                }
            }
        }

        if (views.isEmpty()) {
           List <WorkshopView> notFound = new ArrayList<>();
           notFound.add(contentProvider.getDefaultWorkshopVew("notfound"));
           return notFound;
        }

        return views;
    }

        public List<WorkshopView> getWorkshopsForKidsIndex(){
        checkIsActiveWorkshopsUpToNow();
        List<WorkshopView> views = workshopMapper.mapWorkshopEntityListToView(sortWorkshopsByDate(getActiveWorkshops()));
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                    if (workshopView.getSuitableFor().equals("За деца") || workshopView.getSuitableFor().equals("За всички")){
                        view.add(workshopView);
                        if (view.size() >= 3){
                            break;
                        }
                    }  
                }              
            }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }
    
    public List<WorkshopView> getWorkshopsForKidsCalendar(){
        List<WorkshopView> views = getWorkshopsForCalendar();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (!workshopView.getSuitableFor().equals("За възрастни")){
                    view.add(workshopView);
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    public List<WorkshopView> getWorkshopsForKidsCalendarDiscount(){
        List<WorkshopView> views = getWorkshopsForCalendarDiscount();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (workshopView.getSuitableFor() != "За възрастни"){
                    view.add(workshopView);
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    public List<WorkshopView> getWorkshopsForKidsCalendarPromo(){
        List<WorkshopView> views = getWorkshopsForKidsCalendar();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (!(workshopView.getPromoPrice().isEmpty() || workshopView.getPromoPrice() == null)){
                    view.add(workshopView);
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    public List<WorkshopView> getWorkshopsForKidsCalendarSubscription(){
        List<WorkshopView> views = getWorkshopsForCalendarSubscription();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (workshopView.getSuitableFor() != "За възрастни"){
                    view.add(workshopView);
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    
    public List<WorkshopView> getWorkshopsForAdultsIndex(){
        checkIsActiveWorkshopsUpToNow();
        List<WorkshopView> views = workshopMapper.mapWorkshopEntityListToView(sortWorkshopsByDate(getActiveWorkshops()));
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (workshopView.getSuitableFor().equals("За възрастни") || workshopView.getSuitableFor().equals("За всички")){
                    view.add(workshopView);
                    if (view.size() >= 3){
                        break;
                    }
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }
    
    public List<WorkshopView> getWorkshopsForAdultsCalendar(){
        List<WorkshopView> views = getWorkshopsForCalendar();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if(!workshopView.getSuitableFor().equals("За деца")){
                    view.add(workshopView);
                }
                
            }
        }

        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    public List<WorkshopView> getWorkshopsForAdultsCalendarDiscount(){
        List<WorkshopView> views = getWorkshopsForCalendarDiscount();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (workshopView.getSuitableFor() != "За деца"){
                    view.add(workshopView);
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }
    

    public List<WorkshopView> getWorkshopsForAdultsCalendarPromo(){
        List<WorkshopView> views = getWorkshopsForAdultsCalendar();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (!(workshopView.getPromoPrice().isEmpty() || workshopView.getPromoPrice() == null)){
                    view.add(workshopView);
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    public List<WorkshopView> getWorkshopsForAdultsSubscription(){
        List<WorkshopView> views = getWorkshopsForCalendarSubscription();
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (workshopView.getSuitableFor() != "За деца"){
                    view.add(workshopView);
                }
            }
        }
        
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    public List<WorkshopView> getPrivateEventsForIndex(){
        List<WorkshopView> views = workshopMapper.mapPrivateEventListToView(getActivePrivateEvents());
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (workshopView.getTitle().startsWith("Наем на зала")
                ){
                    view.add(workshopView);
                    if (view.size() >= 3){
                        break;
                    }
                }
            }
        }
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }
    
    public List<WorkshopView> getPrivateWorkshopForIndex(){
        List<WorkshopView> views = workshopMapper.mapPrivateEventListToView(getActivePrivateEvents());
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            for (WorkshopView workshopView : views) {
                if (workshopView.getTitle().startsWith("Работилница")
                ){
                    view.add(workshopView);
                    if (view.size() >= 3){
                        break;
                    }
                }
            }
        }
        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }    
    
    public List<WorkshopView> getPrivateEvents(){
        List<WorkshopView> view = workshopMapper.mapPrivateEventListToView(getActivePrivateEvents());

        if (view.isEmpty()) {
            view.add(contentProvider.getDefaultWorkshopVew("notfound"));
        }

        return view;
    }

    private List<ExhibitionEvent> sortExhibitionsByDate(List<ExhibitionEvent> workshops) {
        checkIsActiveWorkshopsUpToNow();
        for (int i = 0; i < workshops.size() - 1; i++) {
            for (int j = i+1; j < workshops.size(); j++) {
                LocalDate dateI = LocalDate.of(workshops.get(i).getEventDate().getStartYear(), workshops.get(i).getEventDate().getStartMonth(), workshops.get(i).getEventDate().getStartDay());
                LocalDate dateJ = LocalDate.of(workshops.get(j).getEventDate().getStartYear(), workshops.get(j).getEventDate().getStartMonth(), workshops.get(j).getEventDate().getStartDay());
                if(dateI.isAfter(dateJ)){
                 ExhibitionEvent workshop = workshops.get(i);
                 workshops.set(i, workshops.get(j));
                 workshops.set(j, workshop);
                }
            }

        }
        return workshops;
    }

    private List<WorkshopEvent> sortWorkshopsByDate(List<WorkshopEvent> workshops) {
        checkIsActiveWorkshopsUpToNow();
        for (int i = 0; i < workshops.size() - 1; i++) {
            for (int j = i+1; j < workshops.size(); j++) {
                LocalDate dateI = LocalDate.of(workshops.get(i).getEventDate().getStartYear(), workshops.get(i).getEventDate().getStartMonth(), workshops.get(i).getEventDate().getStartDay());
                LocalDate dateJ = LocalDate.of(workshops.get(j).getEventDate().getStartYear(), workshops.get(j).getEventDate().getStartMonth(), workshops.get(j).getEventDate().getStartDay());
                if(dateI.isAfter(dateJ)){
                 WorkshopEvent workshop = workshops.get(i);
                 workshops.set(i, workshops.get(j));
                 workshops.set(j, workshop);
                }
            }

        }
        return workshops;
    }

    private List<WorkshopEvent> getActiveWorkshops(){
        checkIsActiveWorkshopsUpToNow();
        return workshopRepo.findAllByIsActive(true);
    }
    
    private List<ExhibitionEvent> getActiveExhibitions(){
        checkIsActiveWorkshopsUpToNow();
        return workshopRepo.findAllExhibitionsByIsActive(true);
    }

    private List<WorkshopEvent> getDiscountedWorkshops(){
        List<WorkshopEvent> discounted = getActiveWorkshops();
        for (int i = 0; i < discounted.size(); i++){
            if(!discounted.get(i).isDiscounted() || discounted.get(i).isSubscription() || discounted.get(i).isPromo()){

                discounted.remove(i);
                i--;
            }
            
        }
        return discounted;
    }
    
    private List<WorkshopEvent> getSubscriptionWorkshops(){
        List<WorkshopEvent> discounted = getActiveWorkshops();
        for (int i = 0; i < discounted.size(); i++){
            if(!discounted.get(i).isSubscription()){

                discounted.remove(i);
                i--;
            }
            
        }
        return discounted;
    }

    private List<WorkshopEvent> getPromoWorkshops(){
        List<WorkshopEvent> discounted = getActiveWorkshops();
        for (int i = 0; i < discounted.size(); i++){
            if(!discounted.get(i).isPromo()){

                discounted.remove(i);
                i--;
            }
            
        }
        return discounted;
    }

    private List<PrivateEvent> getActivePrivateEvents(){
        checkIsActiveItemsUpToNow();
        return workshopRepo.findAllPrivateEventsByIsActive(true);
    }

    public void checkIsActiveWorkshopsUpToNow(){
        List<WorkshopEvent> workshops = workshopRepo.findAllByIsActive(true);
        List<ExhibitionEvent> exhibitionEvents = workshopRepo.findAllExhibitionEvents();

        if(!workshops.isEmpty() || upToDate.isAfter( dateProvider.getDate())) {
            LocalDate date = dateProvider.getDate();

            for (WorkshopEvent workshop : workshops) {
                LocalDate endDate = LocalDate.of(workshop.getEventDate().getEndYear(),workshop.getEventDate().getEndMonth(),workshop.getEventDate().getEndDay());
                workshop.setActive(!date.isAfter(endDate));
            }
            for (ExhibitionEvent exhibitionEvent : exhibitionEvents) {
                LocalDate endDate = LocalDate.of(exhibitionEvent.getEventDate().getEndYear(),exhibitionEvent.getEventDate().getEndMonth(),exhibitionEvent.getEventDate().getEndDay());
                exhibitionEvent.setActive(!date.isAfter(endDate));
            }
            upToDate = dateProvider.getDate();

        }
    }

    public void checkIsActiveItemsUpToNow(){
        List<WorkshopEvent> workshops = workshopRepo.findAllByIsActive(true);

        if(!workshops.isEmpty() || upToDate.isAfter(dateProvider.getDate())) {
            LocalDate date = dateProvider.getDate();

            for ( WorkshopEvent workshop : workshops) {
                LocalDate enDate = LocalDate.of(workshop.getEventDate().getEndYear(), workshop.getEventDate().getEndMonth(), workshop.getEventDate().getEndDay());

                workshop.setActive(!date.isAfter(enDate));

            }
            upToDate = dateProvider.getDate();

        }
    }

    public WorkshopView getById(String id) {
        BaseWorkshop workshop = workshopRepo.findByID(id);
        
        if (workshop.isWorkshop()){
            return workshopMapper.mapWorkshopEntityToView((WorkshopEvent) workshop);
        } else if(workshop.isExhibition()){
            return workshopMapper.mapExhibitionEntityToView((ExhibitionEvent) workshop);
        } else if (workshop.isPrivateEvent()){
            return workshopMapper.mapPrivateEventEntityToView((PrivateEvent) workshop);
        }
        

        return contentProvider.getDefaultWorkshopVew(id);
    }

    public void init() {
        if (!workshopRepo.init()){
            throw new BadRequestException("Loading error");
        }
    }

    public String getWorkshopDateById(String id){
        WorkshopEvent workshop = (WorkshopEvent) workshopRepo.findByID(id);
        if(workshop==null){
            return "Не е намерен такава работилница";
        }
        return workshop.getEventDate().getStartDate() + "." + workshop.getEventDate().getStartMonth() + "." + workshop.getEventDate().getStartYear() + ".";
    }
}
