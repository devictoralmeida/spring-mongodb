package com.devictoralmeida.spring_mongodb.controllers;

import com.devictoralmeida.spring_mongodb.models.dto.PostDTO;
import com.devictoralmeida.spring_mongodb.models.dto.UserDTO;
import com.devictoralmeida.spring_mongodb.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
  private final UserService service;

  @GetMapping
  public ResponseEntity<List<UserDTO>> findAll() {
    return ResponseEntity.ok().body(service.findAll());
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<UserDTO> findById(@PathVariable String id) {
    return ResponseEntity.ok().body(service.findById(id));
  }

  @GetMapping(value = "/{id}/posts")
  public ResponseEntity<List<PostDTO>> findPosts(@PathVariable String id) {
    return ResponseEntity.ok().body(service.getUserPosts(id));
  }

  @PostMapping
  public ResponseEntity<UserDTO> insert(@RequestBody UserDTO dto) {
    UserDTO response = service.insert(dto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
    return ResponseEntity.created(uri).body(response);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<UserDTO> update(@PathVariable String id, @RequestBody UserDTO dto) {
    return ResponseEntity.ok().body(service.update(id, dto));
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable String id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
