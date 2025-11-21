package com.colortrap.web.init;

import com.colortrap.web.service.WorkshopService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements CommandLineRunner {

    private final WorkshopService workshopService;

    public DataInit(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @Override
    public void run(String... args) {
        workshopService.init();
    }
}
