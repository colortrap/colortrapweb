package com.colortrap.web.service;

import com.colortrap.web.model.entity.Workshop;
import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.repository.WorkshopRepo;
import com.colortrap.web.repository.util.DefaultContentProvider;
import com.colortrap.web.service.util.DateProvider;
import com.colortrap.web.service.util.WorkshopMapper;
import com.colortrap.web.web.error.BadRequestException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkshopService {
    final private WorkshopRepo workshopRepo;
    final private DateProvider dateProvider;
    final private WorkshopMapper workshopMapper;
    final private DefaultContentProvider contentProvider;
    private LocalDate upToDate;

    public WorkshopService(WorkshopRepo workshopRepo, DateProvider dateProvider,
                           WorkshopMapper workshopMapper, DefaultContentProvider contentProvider){
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
        while (views.size() > 36){
            views.remove(36);
        }
        return views;
    }

    public List<WorkshopView> getWorkshopsForIndex(){
        checkIsActiveWorkshopsUpToNow();
        List<WorkshopView> views = workshopMapper.mapWorkshopEntityListToView(sortWorkshopsByDate(getActiveWorkshops()));
        List<WorkshopView> view = new ArrayList<>();

        if(!views.isEmpty()){
            LocalDate date = dateProvider.getDate();
            for (WorkshopView workshopView : views) {
                if (workshopView.getDay() == 0 ||
                        (workshopView.getDay() == date.getDayOfMonth() &&
                        workshopView.getMonth() == date.getMonthValue() &&
                        workshopView.getYear() == date.getYear())
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

    private List<Workshop> sortWorkshopsByDate(List<Workshop> workshops) {
        checkIsActiveWorkshopsUpToNow();
        for (int i = 0; i < workshops.size() - 1; i++) {
            for (int j = i+1; j < workshops.size(); j++) {
                if(workshops.get(i).getDay() > workshops.get(j).getDay()
                        && workshops.get(i).getMonth() >= workshops.get(j).getMonth()
                        && workshops.get(i).getYear() >= workshops.get(j).getYear()
                ){
                 Workshop workshop = workshops.get(i);
                 workshops.set(i, workshops.get(j));
                 workshops.set(j, workshop);
                }
            }

        }
        return workshops;
    }

    private List<Workshop> getActiveWorkshops(){
        checkIsActiveWorkshopsUpToNow();
        return workshopRepo.findAllByIsActive(true);
    }

    public void checkIsActiveWorkshopsUpToNow(){
        List<Workshop> workshops = workshopRepo.findAll();

        if(!workshops.isEmpty() || upToDate.isAfter( dateProvider.getDate())) {
            LocalDate date = dateProvider.getDate();

            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();

            for (Workshop workshop : workshops) {

                workshop.setIsActive(workshop.getDay() == 0 || ((year <= workshop.getYear()) &&
                        (month <= workshop.getMonth() || year != workshop.getYear()) &&
                        (day <= workshop.getDay() ||
                                month != workshop.getMonth()
                                || year != workshop.getYear())));

            }
            upToDate = dateProvider.getDate();

        }
    }

    public WorkshopView getWorkshopById(String id) {
        Workshop workshop = workshopRepo.findByID(id);
        if(workshop==null){
            return contentProvider.getDefaultWorkshopVew(id);
        }
        return workshopMapper.mapWorkshopEntityToView(workshop);
    }

    public void init() {
        if (!workshopRepo.init()){
            throw new BadRequestException("Loading error");
        }
    }
}
