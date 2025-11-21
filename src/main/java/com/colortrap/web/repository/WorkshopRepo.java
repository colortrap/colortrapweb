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
    final private JsonReader jsonReader;
    final private DefaultContentProvider contentProvider;

    public WorkshopRepo(JsonReader jsonReader, DefaultContentProvider contentProvider) {
        this.jsonReader = jsonReader;
        this.contentProvider = contentProvider;
    }

    public boolean init(){         
        workshops = jsonReader.readJsonFile().getWorkshops();

        for (int i = 0; i < workshops.size(); i++) {
            Workshop workshop = workshops.get(i);

            workshop.setId("" + i + workshop.getDay() + workshop.getMonth() + workshop.getYear());
            workshop.setPictureUrl(contentProvider.getPictureUrlByType(workshop.getTitle()));

            if(!workshop.getEventType().equals("Изложба")){
            workshop.setDescription(contentProvider.getDescription(workshop.getTitle(), workshop.getEventType()));
            workshop.setTitle("'" + workshop.getTitle() + "'");
            }
        }
        return !workshops.isEmpty();
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

    public List<Workshop> findAll() {
        return workshops;
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
        return null;
    }
}
