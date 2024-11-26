package com.devictoralmeida.spring_mongodb.services.exceptions;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 2431633034927429254L;

  public ResourceNotFoundException(String msg) {
    super(msg);
  }
}
