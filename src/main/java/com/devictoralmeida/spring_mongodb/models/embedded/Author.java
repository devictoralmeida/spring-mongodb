package com.devictoralmeida.spring_mongodb.models.embedded;

import com.devictoralmeida.spring_mongodb.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Author implements Serializable {
  @Serial
  private static final long serialVersionUID = 2798924062033689875L;

  @Id
  private String id;
  private String name;

  public Author(User user) {
    id = user.getId();
    name = user.getName();
  }
}
