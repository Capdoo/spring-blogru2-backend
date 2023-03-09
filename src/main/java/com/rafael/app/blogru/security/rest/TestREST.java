package com.rafael.app.blogru.security.rest;

import com.rafael.app.blogru.security.dto.TokenDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestREST {


    @GetMapping("/rsr_user")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<Object> testResourceUser(){
        TokenDTO tokenDTO = new TokenDTO("algo","salio","bien");
        return ResponseEntity.ok(tokenDTO);
    }

    @GetMapping("/rsr_admin")
    @PreAuthorize("hasAuthority('admin') or hasAuthority('superadmin')")
    public ResponseEntity<Object> testResourceAdmin(){
        TokenDTO tokenDTO = new TokenDTO("algo","salio","bien");
        return ResponseEntity.ok(tokenDTO);
    }
}
