package com.eduproctor.tut.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequest {
    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    private String email;
    private boolean enabled;
    private String firstName;
    private String lastName;
    @Size(min = 6, message = "Password must have at least 6 characters.")
    private String password;
    @Size(min = 10, max=10, message = "Phone number must have at least 10 digits.")
    private String phone;
    @NotBlank(message = "User name is required.")
    private String userName;
    private String profile;
    @NotBlank(message = "Role name is required.")
    private String roleName;
}
