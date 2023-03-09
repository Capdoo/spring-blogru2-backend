package com.rafael.app.blogru.security.repository;

import com.rafael.app.blogru.security.document.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {

    Optional<Role> findByName(String name);

}
