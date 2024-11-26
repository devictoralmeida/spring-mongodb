package com.devictoralmeida.spring_mongodb.models.entities;

import com.devictoralmeida.spring_mongodb.models.dto.UserDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Document(collection = "users") // Define o nome da coleção no MongoDB
public class User implements Serializable {
  @Serial
  private static final long serialVersionUID = 182265672203113577L;

  @Id
  private String id;
  private String name;
  private String email;

  @DBRef(lazy = true) // Referência para outra coleção (os posts do usuário)
  @Setter(AccessLevel.NONE)
  private List<Post> posts = new ArrayList<>();

  public User(String id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public User(UserDTO dto) {
    name = dto.getName();
    email = dto.getEmail();
  }
}
