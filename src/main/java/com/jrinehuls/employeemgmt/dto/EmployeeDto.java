package com.jrinehuls.employeemgmt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @NotNull(message = "first_name cannot be null")
    @NotBlank(message = "first_name cannot be blank")
    @JsonProperty(value="first_name")
    private String firstName;

    @NotNull(message = "last_name cannot be null")
    @NotBlank(message = "last_name cannot be blank")
    @JsonProperty(value="last_name")
    private String lastName;

    @Email(message = "email must be formatted as a valid email address")
    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @JsonProperty(value="email")
    private String email;

}
