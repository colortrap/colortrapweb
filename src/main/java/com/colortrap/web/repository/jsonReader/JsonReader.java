package com.colortrap.web.repository.jsonReader;

import com.colortrap.web.model.entity.Gallery;
import com.colortrap.web.web.error.BadRequestException;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
public class JsonReader {
    public Gallery readJsonFile() {
        Gson gson = new Gson();
        try (
                InputStream inputStreamReader = getClass().getClassLoader().getResourceAsStream("static/staff.json");
                Reader reader = new InputStreamReader(inputStreamReader, StandardCharsets.UTF_8)) {
            return gson.fromJson(reader, Gallery.class);
        } catch (
                IOException e) {
            throw new BadRequestException("Моля опитайте отново");
        }

    }
}
