package com.rafael.app.blogru.service;

import com.rafael.app.blogru.document.User;
import com.rafael.app.blogru.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User Id Not Found"));
     }
}


