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
        List<WorkshopView> kids = workshopService.getWorkshopsForKidsIndex();
        List<WorkshopView> adults = workshopService.getWorkshopsForAdultsIndex();
        List<WorkshopView> rentItems = workshopService.getRentItemsForIndex();
        List<WorkshopView> workshopItems = workshopService.getWorkshopItemsForIndex();

        ModelAndView model = new ModelAndView("index");
        model.addObject("workshops", workshops);
        model.addObject("kids", kids);
        model.addObject("adults", adults);
        model.addObject("rentItems", rentItems);
        model.addObject("workshopItems", workshopItems);
        return model;
    }

    @Override
    public ModelAndView share() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForShare();
        List<WorkshopView> promos = workshopService.getPromosForShare();

        ModelAndView model =  new ModelAndView("share");
        model.addObject("workshops", workshops);
        model.addObject("promos", promos);
        return model;
    }

    @Override
    public ModelAndView girtCard() {
        return new ModelAndView("girtCard");
    }

    @Override
    public ModelAndView privateParty() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForCalendar();

        ModelAndView model = new ModelAndView("privateParty");
        model.addObject("workshops", workshops);
        return model;
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
