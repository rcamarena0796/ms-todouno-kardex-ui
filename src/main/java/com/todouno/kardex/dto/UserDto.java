package com.todouno.kardex.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * UserDto.
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserDto {

  private String id;
  @NotBlank(message = "username is required")
  private String username;

  @NotBlank(message = "password is required")
  private String password;

  @Email(message = "email should be valid")
  private String email;

  @NotBlank(message = "phoneNumber is required")
  @Pattern(regexp = "(^$|[0-9]{9})", message = "phoneNumber must be valid")
  private String phoneNumber;

}