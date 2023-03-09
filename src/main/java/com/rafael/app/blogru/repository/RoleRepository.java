package com.rafael.app.blogru.repository;

import com.rafael.app.blogru.document.Role;
import com.rafael.app.blogru.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(String name);

}
