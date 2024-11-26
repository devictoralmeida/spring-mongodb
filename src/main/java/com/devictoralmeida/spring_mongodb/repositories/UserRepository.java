package com.devictoralmeida.spring_mongodb.repositories;

import com.devictoralmeida.spring_mongodb.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
