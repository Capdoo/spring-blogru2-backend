package com.rafael.app.blogru.rest;

import com.rafael.app.blogru.document.RefreshToken;
import com.rafael.app.blogru.document.User;
import com.rafael.app.blogru.dto.LoginDTO;
import com.rafael.app.blogru.dto.SignupDTO;
import com.rafael.app.blogru.dto.TokenDTO;
import com.rafael.app.blogru.jwt.JwtHelper;
import com.rafael.app.blogru.repository.RefreshTokenRepository;
import com.rafael.app.blogru.repository.UserRepository;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthREST {

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    RefreshTokenRepository refreshTokenRepository;
    @Autowired
    JwtHelper jwtHelper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        refreshTokenRepository.save(refreshToken);

        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken.getId());

        return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
    }

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDTO signupDTO){
        User user = new User(signupDTO.getUsername(), signupDTO.getEmail(), passwordEncoder.encode(signupDTO.getPassword()));
        userRepository.save(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        refreshTokenRepository.save(refreshToken);

        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken.getId());

        return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
    }
}
