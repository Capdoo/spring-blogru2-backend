package com.rafael.app.blogru.security.service;

import com.rafael.app.blogru.security.document.Role;
import com.rafael.app.blogru.security.document.User;
import com.rafael.app.blogru.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username Not Found"));
    }

     public User findById(String id){

        User userSource = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User Id Not Found"));

         Set<Role> roles = userSource.getRoles();

         System.out.println("Printing roles");
         roles.forEach(System.out::println);

         List<GrantedAuthority> authorities = roles.stream()
                 .map( rol -> new SimpleGrantedAuthority(rol.getName()))
                 .collect(Collectors.toList());
         userSource.setAuthorities(authorities);

        return userSource;
     }
}


