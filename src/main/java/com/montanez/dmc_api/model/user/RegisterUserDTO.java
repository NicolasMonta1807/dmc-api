package com.montanez.dmc_api.model.user;

import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDTO {

  @NotBlank
  @Size(min = 8, max = 30)
  @Indexed(unique = true)
  private String username;

  @Email
  @NotBlank
  @Indexed(unique = true)
  private String email;

  @NotBlank
  @Size(min = 8)
  private String password;
}
