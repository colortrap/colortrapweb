package com.colortrap.web.web.controller.impl;

import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.service.WorkshopService;
import com.colortrap.web.web.controller.HomeController;
import com.colortrap.web.web.error.BadRequestException;
import com.colortrap.web.web.error.BaseApplicationException;
import com.colortrap.web.web.error.ObjectNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
public class HomeControllerImpl implements HomeController {
    private final WorkshopService workshopService;

    public HomeControllerImpl(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @Override
    public ModelAndView index() throws BadRequestException, ObjectNotFoundException {

        List<WorkshopView> workshops = workshopService.getWorkshopsForIndex();

        ModelAndView model = new ModelAndView("index");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView about() {
        return new ModelAndView("about");
    }

    @Override
    public ModelAndView share() {
        return new ModelAndView("share");
    }

    @Override
    public ModelAndView girtCard() {
        return new ModelAndView("girtCard");
    }

    @Override
    public ModelAndView privateParty() {
        return new ModelAndView("privateParty");
    }

    @Override
    public ModelAndView workshopView() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForCalendar();

        ModelAndView model = new ModelAndView("calendar");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView workshopView(String id) {
        WorkshopView workshop = workshopService.getWorkshopById(id);

        ModelAndView model = new ModelAndView("workshop");
        model.addObject("workshop", workshop);
        return model;
    }

    @ExceptionHandler({ObjectNotFoundException.class, BadRequestException.class})
    public ModelAndView handleApplicationExceptions(BaseApplicationException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }

}
