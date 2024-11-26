package com.devictoralmeida.spring_mongodb.models.dto;

import com.devictoralmeida.spring_mongodb.models.embedded.Author;
import com.devictoralmeida.spring_mongodb.models.embedded.Comment;
import com.devictoralmeida.spring_mongodb.models.entities.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO implements Serializable {
  @Serial
  private static final long serialVersionUID = 1L;

  private String id;
  private Instant moment;
  private String title;
  private String body;
  private Author author;

  @Setter(AccessLevel.NONE)
  private List<Comment> comments = new ArrayList<>();

  public PostDTO(Post entity) {
    id = entity.getId();
    moment = entity.getMoment();
    title = entity.getTitle();
    body = entity.getBody();
    author = entity.getAuthor();
    comments.addAll(entity.getComments());
  }
}
