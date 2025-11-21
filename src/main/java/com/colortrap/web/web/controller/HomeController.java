package com.colortrap.web.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
public interface HomeController {

    @GetMapping("/")
    ModelAndView index();

    @GetMapping("/about")
    ModelAndView about();

    @GetMapping("/share")
    ModelAndView share();

    @GetMapping("/girtCard")
    ModelAndView girtCard();

    @GetMapping("/privateParty")
    ModelAndView privateParty();

    @GetMapping("/calendar")
    ModelAndView workshopView();

    @GetMapping("/{id}")
    ModelAndView workshopView(@PathVariable String id);
}
