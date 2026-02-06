package com.colortrap.web.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationDTO {
    
    @NotNull(message = "Полете не може да е празно!")
    private String username;
    
    @Email(message = "Въведете валиден email!")
    @NotNull(message = "Полете не може да е празно!")
    private String email;

    @NotNull(message = "Полете не може да е празно!")
    private String tel;

    @NotNull(message = "Полете не може да е празно!")
    private String count; 
}
