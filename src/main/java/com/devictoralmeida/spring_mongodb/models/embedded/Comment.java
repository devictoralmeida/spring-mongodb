package com.devictoralmeida.spring_mongodb.models.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Comment implements Serializable {
  @Serial
  private static final long serialVersionUID = -5550559786821822367L;

  private String text;
  private Instant moment;
  private Author author;
}
