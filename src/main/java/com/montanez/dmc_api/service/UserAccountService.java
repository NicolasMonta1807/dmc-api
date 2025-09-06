package com.montanez.dmc_api.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.montanez.dmc_api.data.UserRepository;
import com.montanez.dmc_api.model.user.RegisterUserDTO;
import com.montanez.dmc_api.model.user.UserAccount;
import com.montanez.dmc_api.model.user.UserSimpleDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserSimpleDTO registerUser(RegisterUserDTO userDTO) {
    UserAccount user = UserAccount.builder()
        .username(userDTO.getUsername())
        .email(userDTO.getEmail())
        .passwordHash(passwordEncoder.encode(userDTO.getPassword()))
        .build();

    // TODO : Handle existing user
    user = userRepository.save(user);

    return UserSimpleDTO.builder()
        .id(user.getId())
        .username(user.getUsername())
        .email(user.getEmail())
        .build();
  }

}
