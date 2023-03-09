package com.rafael.app.blogru.util;

import com.rafael.app.blogru.document.Role;
import com.rafael.app.blogru.repository.RoleRepository;
import com.rafael.app.blogru.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    RoleService roleService;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {

        if (roleService.readByName("user") == null){
            Role role = new Role("user");
            roleRepository.save(role);
        }

        if (roleService.readByName("creator") == null){
            Role role = new Role("creator");
            roleRepository.save(role);
        }

        if (roleService.readByName("admin") == null){
            Role role = new Role("admin");
            roleRepository.save(role);
        }

        if (roleService.readByName("superadmin") == null){
            Role role = new Role("superadmin");
            roleRepository.save(role);
        }

    }
}
