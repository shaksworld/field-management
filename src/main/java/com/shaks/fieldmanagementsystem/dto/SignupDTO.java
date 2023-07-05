package com.shaks.fieldmanagementsystem.dto;

import com.shaks.fieldmanagementsystem.enums.RoleType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SignupDTO {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;

    private String firstName;

    private String email;

    private String lastName;

    private String phoneNumber;

    private String region;

    private String state;

    private String country;


}
