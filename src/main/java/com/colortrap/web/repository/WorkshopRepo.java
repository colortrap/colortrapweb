package com.colortrap.web.repository;

import com.colortrap.web.model.entity.Workshop;
import com.colortrap.web.repository.jsonReader.JsonReader;
import com.colortrap.web.repository.util.DefaultContentProvider;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkshopRepo {
    private List<Workshop> workshops;
    private List<Workshop> items;
    private List<Workshop> stuff;
    final private JsonReader jsonReader;
    final private DefaultContentProvider contentProvider;

    public WorkshopRepo(JsonReader jsonReader, DefaultContentProvider contentProvider) {
        this.jsonReader = jsonReader;
        this.contentProvider = contentProvider;
        items = new ArrayList<>();
        workshops = new ArrayList<>();
    }

    public boolean init(){         
        stuff = jsonReader.readJsonFile().getWorkshops();
        

        for (int i = 0; i < stuff.size(); i++) {
            Workshop workshop = stuff.get(i);

            workshop.setId("" + i + workshop.getDay() + workshop.getMonth() + workshop.getYear());
            if(workshop.getEventType().equals("Частно събитие")){
                items.add(workshop);

                workshop.setPictureUrl(contentProvider.getPictureUrlByType(workshop.getEventType()));
            } else {
                workshops.add(workshop);
                workshop.setPictureUrl(contentProvider.getPictureUrlByTitle(workshop.getTitle()));
            }

            if(!(workshop.getEventType().equals("Изложба") || workshop.getEventType().equals("Частно събитие"))){
            workshop.setDescription(contentProvider.getDescription(workshop.getTitle(), workshop.getEventType()));
            workshop.setTitle(contentProvider.getTitle(workshop.getTitle(), workshop.getEventType()));
            }
        }
        return !stuff.isEmpty();
    }

    public List<Workshop> findAllByIsActive(boolean isActive) {
        List<Workshop> list = new ArrayList<>();
        for (Workshop workshop : workshops) {
            if (workshop.getIsActive() == isActive) {
                list.add(workshop);
            }
        }
        return list;
    }

    public List<Workshop> findAllItemsByIsActive(boolean isActive) {
        List<Workshop> list = new ArrayList<>();
        for (Workshop workshop : items) {
            if (workshop.getIsActive() == isActive) {
                list.add(workshop);
            }
        }
        return list;
    }

    public List<Workshop> findAll() {
        return workshops;
    }

    public List<Workshop> findAllItems() {
        return items;
    }

    public Workshop findByID(String id) {
        if(!workshops.isEmpty()){
            for (Workshop workshop : workshops) {
                String workshopId = workshop.getId();
                if (workshopId.equals(id)) {
                    return workshop;
                }
            }
        }
        if(!items.isEmpty()){
            for (Workshop item : items) {
                String workshopId = item.getId();
                if (workshopId.equals(id)) {
                    return item;
                }
            }
        }
        return null;
    }
}
