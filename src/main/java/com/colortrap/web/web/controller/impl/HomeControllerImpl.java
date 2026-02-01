package com.colortrap.web.web.controller.impl;

import com.colortrap.web.model.dto.RegistrationDTO;
import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.service.WorkshopService;
import com.colortrap.web.web.controller.HomeController;
import com.colortrap.web.web.error.BadRequestException;
import com.colortrap.web.web.error.BaseApplicationException;
import com.colortrap.web.web.error.ObjectNotFoundException;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public ModelAndView promo() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForShare();
        List<WorkshopView> promos = workshopService.getPromosForShare();

        ModelAndView model =  new ModelAndView("promo");
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
        List<WorkshopView> workshops = workshopService.getWorkshopItems();

        ModelAndView model = new ModelAndView("privateParty");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarAll2() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForCalendar2();

        ModelAndView model = new ModelAndView("calendar2");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarAll5() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForCalendar5();

        ModelAndView model = new ModelAndView("calendar5");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarAllPromo() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForCalendarPromo();

        ModelAndView model = new ModelAndView("calendar");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarAllNormal() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForCalendar();

        ModelAndView model = new ModelAndView("calendar");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarForKids2() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForKidsCalendar();

        ModelAndView model = new ModelAndView("forkids2");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarForKids5() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForKidsCalendar();

        ModelAndView model = new ModelAndView("forkids5");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarForKidNnormal() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForKidsCalendar();

        ModelAndView model = new ModelAndView("forkids");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarForAdults2() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForAdultsCalendar();

        ModelAndView model = new ModelAndView("foradults2");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarForAdults5() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForAdultsCalendar();

        ModelAndView model = new ModelAndView("foradults5");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarForAdultsNormal() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForAdultsCalendar();

        ModelAndView model = new ModelAndView("foradults");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView calendarGallery() {
        List<WorkshopView> workshops = workshopService.getWorkshopsForGalleryCalendar();

        ModelAndView model = new ModelAndView("gallery");
        model.addObject("workshops", workshops);
        return model;
    }

    @Override
    public ModelAndView workshopView(String id) {
        WorkshopView workshop = workshopService.getById(id);
        if(workshop.getEventType().equals("Изложба")){

        ModelAndView model = new ModelAndView("workshop");
        model.addObject("workshop", workshop);
        return model;
        }

        if(workshop.getEventType().equals("Частно събитие")){

        ModelAndView model = new ModelAndView("item");
        model.addObject("workshop", workshop);
        return model;
        }

        ModelAndView model = new ModelAndView("workshop");
        model.addObject("workshop", workshop);
        return model;
    }

    @Override
    public ModelAndView workshopRegistration(String id, @Valid RegistrationDTO registrationDTO,
            BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'workshopRegistration'");
    }
    
    @Override
    public RegistrationDTO initRegistrationForm() {
        return new RegistrationDTO();
    }

    @ExceptionHandler({ObjectNotFoundException.class, BadRequestException.class})
    public ModelAndView handleApplicationExceptions(BaseApplicationException e) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("message", e.getMessage());
        modelAndView.addObject("statusCode", e.getStatusCode());

        return modelAndView;
    }

}
