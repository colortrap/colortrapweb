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
    
    @NotNull(message = "Полето не може да е празно!")
    private String username;
    
    @Email(message = "Въведете валиден email!")
    @NotNull(message = "Полето не може да е празно!")
    private String email;

    @NotNull(message = "Полето не може да е празно!")
    private String tel;

    @NotNull(message = "Полето не може да е празно!")
    private String count;
    
    private String subscription;
}
