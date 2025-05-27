package com.example.OnlineLibrary.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

  @NotEmpty(message = "First name is required")
  @NotBlank(message = "First name is required")
  private String firstName;
  @NotEmpty(message = "Last name is required")
  @NotBlank(message = "Last name is required")
  private String lastName;
  @Email(message = "Email is not formatted correctly")
  @NotEmpty(message = "Email is required")
  @NotBlank(message = "Email is required")
  private String email;
  @NotEmpty(message = "Password name is required")
  @NotBlank(message = "Password name is required")
  @Size(min = 4, message = "Password should be 4 characters long minimum")
  private String password;

}
