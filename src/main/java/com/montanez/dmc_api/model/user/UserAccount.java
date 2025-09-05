package com.montanez.dmc_api.model.user;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.montanez.dmc_api.model.inventory.InventoryItem;

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
@Document("user_accounts")
public class UserAccount {

  @Id
  private ObjectId id;

  @NotBlank
  @Size(min = 8, max = 30)
  @Indexed(unique = true)
  private String username;

  @Email
  @NotBlank
  @Indexed(unique = true)
  private String email;

  @Builder.Default
  private List<InventoryItem> inventory = new ArrayList<>();
}
