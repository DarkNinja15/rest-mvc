package com.example.restweb.springrest.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;

    @NotBlank(message = "Validation Error: Title cannot be empty!")
    private String title;

    @AssertTrue(message = "Validation Error: Department can not be inactive!")
    private Boolean isActive;

    @PastOrPresent(message = "Validation Error: Date is not a valid past date!")
    private LocalDate createdAt;
}
