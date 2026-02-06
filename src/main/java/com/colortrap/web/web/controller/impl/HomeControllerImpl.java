package com.colortrap.web.web.controller.impl;

import com.colortrap.web.model.dto.RegistrationDTO;
import com.colortrap.web.model.view.WorkshopView;
import com.colortrap.web.service.EmailService;
import com.colortrap.web.service.WorkshopService;
import com.colortrap.web.web.controller.HomeController;
import com.colortrap.web.web.error.BadRequestException;
import com.colortrap.web.web.error.BaseApplicationException;
import com.colortrap.web.web.error.ObjectNotFoundException;

import jakarta.mail.MessagingException;
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
    private final EmailService emailService;

    public HomeControllerImpl(WorkshopService workshopService, EmailService emailService) {
        this.workshopService = workshopService;
        this.emailService = emailService;
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

        ModelAndView model = new ModelAndView("exhebit");
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

        ModelAndView modelAndView = new ModelAndView("redirect:/workshop-pncw/" + id);

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("registrationDTO", registrationDTO);
            redirectAttributes
                    .addFlashAttribute("org.springframework.validation.BindingResult.registrationDTO",
                            bindingResult);
        } else {
            WorkshopView workshop = workshopService.getById(id);
            String textTo =  "Здравете "+ registrationDTO.getUsername() + ",\n\n" +
            "Заявката за резервация е успешно подадена. Ще се свържем с вас на предоставения от вас телефон " + registrationDTO.getTel() + " за потвърждаването й.\n\n" + 
            "За допълнително информация не се колебайте да се свържете с нас на телефон: 0894 793 440 или 032/517 735!\n\n" + 
            "Лек и успешен ден от екипа на COLORTRAP!";
            String reportEmail = "colortrap.ltd@gmail.com";
            String textReport = "Направена е резервация!\n\n" +
            "На име: " + registrationDTO.getUsername() + "\n\n" + 
            "Телефон: " + registrationDTO.getTel() + "\n\n" + 
            "Емаил: " + registrationDTO.getEmail() + "\n\n" +
            "За: " + workshop.getEventType() + "->" + workshop.getTitle() + "\n\n" + 
            "Заявка за брой участия: " + registrationDTO.getCount() + "\n\n" +  
            "За дата: " + workshop.getDay() + "." + workshop.getMonth() + "." + workshop.getYear() + "." + "\n\n" +
            "година, месец, дата" + 
            "Лек и успешен ден!";
            
            try {
                emailService.sendReportEmail(registrationDTO.getEmail(), reportEmail, textTo, textReport);
            } catch (MessagingException e) {
                throw new BadRequestException(id);
            }

            redirectAttributes.addFlashAttribute("sent", "Успешно направена заявка за резервация. Очаквайте да се свържем с вас за потвърждението й!");          
        }

        return modelAndView;
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
