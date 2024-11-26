package com.devictoralmeida.spring_mongodb.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError implements Serializable {
  @Serial
  private static final long serialVersionUID = 5519293668206216535L;

  private Instant timestamp;
  private Integer status;
  private String error;
  private String message;
  private String path;
}
