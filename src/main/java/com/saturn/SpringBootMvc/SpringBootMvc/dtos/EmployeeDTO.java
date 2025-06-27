package com.saturn.SpringBootMvc.SpringBootMvc.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "name cannot be blank")
    @Size(min = 3, max = 20, message = "no of character in name should be in between 3 - 20")
    private String name;

    @Email(message = "email not in correct format")
    private String email;

    @Max(value = 40, message = "age should be below 40")
    @Min(value = 10, message = "age should be above 10")
    private Integer age;

    @NotNull(message = "points cannot be blank")
    @Positive(message = "points should be positive")
    @Digits(integer = 2, fraction = 2, message = "points should be in the format of XX.YY")
    private Double points;

    @Pattern(regexp = "^(DRIVER|ENGINEER)$", message = "role can be DRIVER OR ENGINEER ")
    @NotBlank(message = "role cannot be blank")
    private String role; //DRIVER OR ENGINEER

    @PastOrPresent(message = "date of joining cannot be from future")
    private LocalDate dateOfJoining;

    private Boolean isActive;

}
