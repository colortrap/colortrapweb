package com.colortrap.web.service.util;

import com.colortrap.web.model.entity.Workshop;
import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.repository.util.DefaultContentProvider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WorkshopMapper {
    final private DefaultContentProvider contentProvider;

    public WorkshopMapper(DefaultContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }


    public List<WorkshopView> mapWorkshopEntityListToView(List<Workshop> all) {
        List<WorkshopView> viewList = new ArrayList<>();
        if (!all.isEmpty()) {
            for (Workshop workshop : all) {
                viewList.add(mapWorkshopEntityToView(workshop));
            }
        }
        return viewList;
    }

    public WorkshopView mapWorkshopEntityToView (Workshop workshop){
        WorkshopView view = new WorkshopView();

        view.setId(workshop.getId());
        view.setDay(workshop.getDay());
        view.setMonth(workshop.getMonth());
        view.setYear(workshop.getYear());
        view.setDatePicUrl(contentProvider.getCalendarPicURL(workshop.getDay(), workshop.getMonth()));
        view.setStartAt(workshop.getStartAt());
        view.setSuitableFor(contentProvider.getSuitableForString(workshop.getSuitableFor()));
        view.setEventType(workshop.getEventType());
        view.setTypePicUrl(contentProvider.getTypePicUrl(workshop.getEventType()));
        view.setPictureUrl(workshop.getPictureUrl());
        view.setTitle(workshop.getTitle());
        view.setPrice(workshop.getPrice());
        view.setDescription(workshop.getDescription());
        view.setIsActive(workshop.getIsActive());

        return view;
    }

}
