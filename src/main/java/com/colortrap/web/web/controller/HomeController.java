package com.colortrap.web.web.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.colortrap.web.model.dto.RegistrationDTO;

import jakarta.validation.Valid;

@RequestMapping("/")
public interface HomeController {

    @GetMapping("/")
    ModelAndView index();

    @GetMapping("/promo")
    ModelAndView promo();

    @GetMapping("/girtCard")
    ModelAndView girtCard();

    @GetMapping("/privateParty")
    ModelAndView privateParty();

    @GetMapping("/calendar-ptc-all")
    ModelAndView calendarAll2();

    @GetMapping("/calendar-pfc-all")
    ModelAndView calendarAll5();
    
    @GetMapping("/calendar-ppc-all")
    ModelAndView calendarAllPromo();
    
    @GetMapping("/calendar-pnc-all")
    ModelAndView calendarAllNormal();
    
    @GetMapping("/calendar-ptc-forkids")
    ModelAndView calendarForKids2();

    @GetMapping("/calendar-pfc-forkids")
    ModelAndView calendarForKids5();
    
    @GetMapping("/calendar-pnc-forkids")
    ModelAndView calendarForKidNnormal();
    
    @GetMapping("/calendar-ptc-foradults")
    ModelAndView calendarForAdults2();

    @GetMapping("/calendar-pfc-foradults")
    ModelAndView calendarForAdults5();
    
    @GetMapping("/calendar-pnc-foradults")
    ModelAndView calendarForAdultsNormal();
    
    @GetMapping("/calendar-pnc-gallery")
    ModelAndView calendarGallery();   

    @GetMapping("/workshop-pncw/{id}")
    ModelAndView workshopView(@PathVariable String id);

    @PostMapping("/workshop-pncw/{id}")
    ModelAndView workshopRegistration(@PathVariable String id, @Valid RegistrationDTO registrationDTO,
                            BindingResult bindingResult,
                            RedirectAttributes redirectAttributes);
                            
    @ModelAttribute("registrationDTO")
    RegistrationDTO initRegistrationForm();
}
