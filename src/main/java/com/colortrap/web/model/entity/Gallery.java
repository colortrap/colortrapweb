package com.colortrap.web.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import com.colortrap.web.model.dto.WorkshopDTO;

@Getter
@Setter
@NoArgsConstructor
public class Gallery {
    private List<WorkshopDTO> workshops;
}
