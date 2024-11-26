package com.devictoralmeida.spring_mongodb.models.entities;

import com.devictoralmeida.spring_mongodb.models.embedded.Author;
import com.devictoralmeida.spring_mongodb.models.embedded.Comment;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "posts")
public class Post implements Serializable {
  @Serial
  private static final long serialVersionUID = -6144982571975023044L;

  @Id
  private String id;

  private Instant moment;
  private String title;
  private String body;
  private Author author;

  @Setter(AccessLevel.NONE)
  private List<Comment> comments = new ArrayList<>();

  public Post(String id, Instant moment, String title, String body, Author author) {
    this.id = id;
    this.moment = moment;
    this.title = title;
    this.body = body;
    this.author = author;
  }
}
