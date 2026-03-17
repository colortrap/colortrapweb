package com.colortrap.web.repository;

import com.colortrap.web.model.dto.WorkshopDTO;
import com.colortrap.web.model.entity.BaseWorkshop;
import com.colortrap.web.model.entity.ExhibitionEvent;
import com.colortrap.web.model.entity.PrivateEvent;
import com.colortrap.web.model.entity.WorkshopEvent;
import com.colortrap.web.repository.jsonReader.JsonReader;
import com.colortrap.web.repository.util.WorkshopDTOtoEntityMapper;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkshopRepo {
    private List<BaseWorkshop> workshops;
    
    final private JsonReader jsonReader;
    final private WorkshopDTOtoEntityMapper mapper;

    public WorkshopRepo(JsonReader jsonReader, WorkshopDTOtoEntityMapper mapper) {
        this.jsonReader = jsonReader;
        this.mapper = mapper;
        workshops = new ArrayList<>();
    }

    public boolean init(){         
        List<WorkshopDTO> stuff = jsonReader.readJsonFile().getWorkshops();
        
        for (int i = 0; i < stuff.size(); i++) {
            BaseWorkshop workshop = mapper.mapToEntity(stuff.get(i));
            workshops.add(workshop);
        }
        return !stuff.isEmpty();
    }

    public List<WorkshopEvent> findAllByIsActive(boolean isActive) {
        List<WorkshopEvent> list = new ArrayList<>();
        for (BaseWorkshop workshop : workshops) {
            if (workshop.isActive() == isActive&& workshop.isWorkshop()) {
                list.add((WorkshopEvent) workshop);
            }
        }
        return list;
    }

    public List<ExhibitionEvent> findAllExhibitionsByIsActive(boolean isActive) {
        List<ExhibitionEvent> list = findAllExhibitionEvents();
        for (BaseWorkshop workshop : workshops) {
            if (workshop.isActive() && workshop.isExhibition()) {
                list.add((ExhibitionEvent) workshop);
            }
        }
        return list;
    }

    public List<PrivateEvent> findAllPrivateEventsByIsActive(boolean isActive) {
        List<PrivateEvent> list = findAllPrivateEvent();
        for (BaseWorkshop workshop : workshops) {
            if (workshop.isActive() && workshop.isPrivateEvent()) {
                list.add((PrivateEvent) workshop);
            }
        }
        return list;
    }

    public List<BaseWorkshop> findAll() {
        List<WorkshopEvent> list = new ArrayList<>();
        for (BaseWorkshop baseWorkshop : workshops) {
            if(baseWorkshop.isWorkshop()){
                list.add((WorkshopEvent) baseWorkshop);
            }
        }
        return workshops;
    }

    public List<PrivateEvent> findAllPrivateEvent() {
        List<PrivateEvent> list = new ArrayList<>();
        for (BaseWorkshop workshop : workshops) {
            if (workshop.isPrivateEvent()) {
                list.add((PrivateEvent) workshop);
            }
        }
        return list;
    }

    public List<ExhibitionEvent> findAllExhibitionEvents() {
        List<ExhibitionEvent> list = new ArrayList<>();
        for (BaseWorkshop workshop : workshops) {
            if (workshop.isExhibition()) {
                list.add((ExhibitionEvent) workshop);
            }
        }
        return list;
    }

    public BaseWorkshop findByID(String id) {
        if(!workshops.isEmpty()){
            for (BaseWorkshop workshop : workshops) {
                String workshopId = workshop.getId();
                if (workshopId.equals(id)) {
                    return workshop;
                }
            }
        }
        return null;
    }
}
