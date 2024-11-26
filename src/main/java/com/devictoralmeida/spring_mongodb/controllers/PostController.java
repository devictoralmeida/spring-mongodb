package com.devictoralmeida.spring_mongodb.controllers;

import com.devictoralmeida.spring_mongodb.models.dto.PostDTO;
import com.devictoralmeida.spring_mongodb.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/posts")
public class PostController {
  private final PostService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<PostDTO> findById(@PathVariable String id) {
    return ResponseEntity.ok().body(service.findById(id));
  }

  @GetMapping(value = "/titlesearch")
  public ResponseEntity<List<PostDTO>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
    return ResponseEntity.ok().body(service.findByTitle(text));
  }

  @GetMapping(value = "/fullsearch")
  public ResponseEntity<List<PostDTO>> fullSearch(
          @RequestParam(value = "text", defaultValue = "") String text,
          @RequestParam(value = "start", defaultValue = "") String start,
          @RequestParam(value = "end", defaultValue = "") String end) {
    return ResponseEntity.ok().body(service.fullSearch(text, start, end));
  }
}
