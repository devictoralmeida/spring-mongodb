package com.devictoralmeida.spring_mongodb.config;

import com.devictoralmeida.spring_mongodb.models.embedded.Author;
import com.devictoralmeida.spring_mongodb.models.embedded.Comment;
import com.devictoralmeida.spring_mongodb.models.entities.Post;
import com.devictoralmeida.spring_mongodb.models.entities.User;
import com.devictoralmeida.spring_mongodb.repositories.PostRepository;
import com.devictoralmeida.spring_mongodb.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test") // Define que a configuração é específica para o perfil de teste
@RequiredArgsConstructor
public class TestConfig {
  private final UserRepository userRepository;
  private final PostRepository postRepository;

  // Essa anotação faz com que o método seja executado assim que a aplicação for iniciada
  @PostConstruct
  public void init() {
    // Limpa a coleção ao iniciar o perfil de teste
    userRepository.deleteAll();
    postRepository.deleteAll();


    User maria = new User(null, "Maria Brown", "maria@mail.com");
    User alex = new User(null, "Alex Sousa", "alex@mail.com");
    User bob = new User(null, "Bob Ferguson", "bob@mail.com");

    userRepository.saveAll(Arrays.asList(maria, alex, bob));

    Post post1 = new Post(null, Instant.parse("2021-02-13T11:15:01Z"), "Partiu viagem", "Vou viajar para Fortaleza. Abraços!", new Author(maria));
    Post post2 = new Post(null, Instant.parse("2021-02-14T10:05:49Z"), "Bom dia", "Acordei feliz hoje!", new Author(maria));

    Comment c1 = new Comment("Boa viagem mano!", Instant.parse("2021-02-13T14:30:01Z"), new Author(alex));
    Comment c2 = new Comment("Aproveite", Instant.parse("2021-02-13T15:38:05Z"), new Author(bob));
    Comment c3 = new Comment("Tenha um ótimo dia!", Instant.parse("2021-02-14T12:34:26Z"), new Author(alex));

    post1.getComments().addAll(Arrays.asList(c1, c2));
    post2.getComments().addAll(Arrays.asList(c3));

    postRepository.saveAll(Arrays.asList(post1, post2));

    maria.getPosts().addAll(Arrays.asList(post1, post2));
    userRepository.save(maria);
  }
}
