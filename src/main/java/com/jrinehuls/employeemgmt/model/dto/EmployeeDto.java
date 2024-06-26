package com.jrinehuls.employeemgmt.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotNull(message = "First name cannot be null")
    @NotBlank(message = "First name cannot be blank")
    @JsonProperty(value = "first_name")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @NotBlank(message = "Last name cannot be blank")
    @JsonProperty(value = "last_name")
    private String lastName;

    @Email(message = "Email must be formatted as a valid email address")
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @JsonProperty(value = "email")
    private String email;

}
