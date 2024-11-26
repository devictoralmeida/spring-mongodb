package com.devictoralmeida.spring_mongodb.models.dto;

import com.devictoralmeida.spring_mongodb.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private String id;
  private String name;
  private String email;

  public UserDTO(User entity) {
    id = entity.getId();
    name = entity.getName();
    email = entity.getEmail();
  }
}
