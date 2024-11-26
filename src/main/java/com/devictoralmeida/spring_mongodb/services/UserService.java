package com.devictoralmeida.spring_mongodb.services;

import com.devictoralmeida.spring_mongodb.models.dto.PostDTO;
import com.devictoralmeida.spring_mongodb.models.dto.UserDTO;
import com.devictoralmeida.spring_mongodb.models.entities.User;
import com.devictoralmeida.spring_mongodb.repositories.UserRepository;
import com.devictoralmeida.spring_mongodb.services.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public List<UserDTO> findAll() {
    return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
  }

  public List<PostDTO> getUserPosts(String id) {
    User user = getEntityById(id);
    return user.getPosts().stream().map(PostDTO::new).collect(Collectors.toList());
  }

  public UserDTO findById(String id) {
    return new UserDTO(getEntityById(id));
  }

  public User getEntityById(String id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
  }

  public UserDTO insert(UserDTO dto) {
    User entity = new User(dto);
    userRepository.insert(entity); // Para criar é o insert
    return new UserDTO(entity);
  }

  public UserDTO update(String id, UserDTO dto) {
    User entity = getEntityById(id);
    copyDtoToEntity(dto, entity);
    entity = userRepository.save(entity); // Para atualizar é o save mesmo.
    return new UserDTO(entity);
  }

  public void delete(String id) {
    getEntityById(id);
    userRepository.deleteById(id);
  }


  private void copyDtoToEntity(UserDTO dto, User entity) {
    entity.setName(dto.getName());
    entity.setEmail(dto.getEmail());
  }
}
