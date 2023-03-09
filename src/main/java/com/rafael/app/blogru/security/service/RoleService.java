package com.rafael.app.blogru.security.service;

import com.rafael.app.blogru.security.document.Role;
import com.rafael.app.blogru.security.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role readByName(String name){
        return roleRepository.findByName(name).orElse(null);
    }

}
