package com.example.restweb.springrest.dto;

import com.example.restweb.springrest.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Validation Error: Name cannot be empty!")
    @Size(min = 3, max = 10, message = "Validation Error: Number of characters in name should be between 3 and 10!")
    public String name;

    @NotBlank(message = "Validation Error: Email cannot be empty!")
    @Email(message = "Validation Error: Email is not valid!")
    private String email;

    @NotBlank(message = "Validation Error: Age cannot be empty!")
    @Max(value = 80, message = "Validation Error: Age of employee cannot be greater than 80!")
    @Min(value = 18, message = "Validation Error: Age of employee cannot be lesser than 18!")
    private Integer age;

    @NotBlank(message = "Validation Error: Role cannot be empty!")
//    @Pattern(regexp = "^(ADMIN|USER)$",message = "Validation Error: Invalid employee role!")
    @EmployeeRoleValidation
    private String role;

    @NotBlank(message = "Validation Error: Salary cannot be empty!")
    @Digits(integer = 6,fraction = 2, message = "Validation Error: Salary is not in the required range!")
    @DecimalMin(value = "100.50", message = "Validation Error: Salary should be greater than 100.50!")
    @DecimalMin(value = "100000.99", message = "Validation Error: Salary should be lesser than 100000.99!")
    private Double salary;

    @PastOrPresent(message = "Validation Error: Date is not a valid past date!")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Validation Error: Employee should be active!")
    private Boolean isActive;
}
