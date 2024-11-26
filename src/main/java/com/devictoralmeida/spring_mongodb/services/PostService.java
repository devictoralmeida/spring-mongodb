package com.devictoralmeida.spring_mongodb.services;

import com.devictoralmeida.spring_mongodb.models.dto.PostDTO;
import com.devictoralmeida.spring_mongodb.models.entities.Post;
import com.devictoralmeida.spring_mongodb.repositories.PostRepository;
import com.devictoralmeida.spring_mongodb.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
  private final PostRepository postRepository;

  public PostDTO findById(String id) {
    return new PostDTO(getEntityById(id));
  }

  public Post getEntityById(String id) {
    return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post não encontrado"));
  }

  public List<PostDTO> findByTitle(String text) {
    return postRepository.searchTitle(text).stream().map(PostDTO::new).collect(Collectors.toList());
  }

  public List<PostDTO> fullSearch(String text, String start, String end) {
    Instant startMoment = convertMoment(start, Instant.ofEpochMilli(0L));
    Instant endMoment = convertMoment(end, Instant.now());
    return postRepository.fullSearch(text, startMoment, endMoment).stream().map(PostDTO::new).collect(Collectors.toList());
  }

  private Instant convertMoment(String originalString, Instant alternativeValue) {
    try {
      return Instant.parse(originalString);
    } catch (DateTimeParseException e) {
      return alternativeValue;
    }
  }
}
