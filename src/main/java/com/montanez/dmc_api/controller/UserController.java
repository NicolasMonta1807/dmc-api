package com.montanez.dmc_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montanez.dmc_api.model.user.RegisterUserDTO;
import com.montanez.dmc_api.model.user.UserSimpleDTO;
import com.montanez.dmc_api.service.UserAccountService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

  private final UserAccountService userAccountService;

  @PostMapping("/register")
  public ResponseEntity<UserSimpleDTO> postRegisterUser(@Valid @RequestBody RegisterUserDTO userDTO) {
    UserSimpleDTO registeredUser = userAccountService.registerUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
  }

}
