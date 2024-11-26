package com.devictoralmeida.spring_mongodb.repositories;

import com.devictoralmeida.spring_mongodb.models.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {
  // Para fazer a busca de textos, vamos precisar utilizar o $regex, o ?0 significa que é o primeiro parâmetro do metodo
  // O i corresponde à Insensibilidade a maiúsculas
  @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
  List<Post> searchTitle(String text);

  List<Post> findByTitleContainingIgnoreCase(String text);

  // Busca pelo texto no título, OU no corpo, OU nos comentários
  // (moment >= minDate) AND (moment <= maxDate) AND (title OU body OU comments.text), temos 3 condições com o AND
  // { $and: [ { <expressão 1> }, { <expressão 2> }, { <expressão 3> } ] }
  // { $or: [ { <expressão 1> }, { <expressão 2> }, { <expressão 3> } ] }
  @Query("{ $and: [ { 'moment': {$gte: ?1} }, { 'moment': { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
  List<Post> fullSearch(String text, Instant minDate, Instant maxDate);
}
