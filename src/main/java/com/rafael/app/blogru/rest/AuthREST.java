package com.rafael.app.blogru.rest;

import com.rafael.app.blogru.document.RefreshToken;
import com.rafael.app.blogru.document.Role;
import com.rafael.app.blogru.document.User;
import com.rafael.app.blogru.dto.LoginDTO;
import com.rafael.app.blogru.dto.SignupDTO;
import com.rafael.app.blogru.dto.TokenDTO;
import com.rafael.app.blogru.jwt.JwtHelper;
import com.rafael.app.blogru.repository.RefreshTokenRepository;
import com.rafael.app.blogru.repository.UserRepository;
import com.rafael.app.blogru.service.RoleService;
import com.rafael.app.blogru.service.UserService;
import org.apache.tomcat.Jar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
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
import java.util.HashSet;
import java.util.Set;

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
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    //For multiple devices
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

        Set<Role> roles = new HashSet<>();
        roles.add(roleService.readByName("user"));

        if (signupDTO.getRoles().contains("admin")){
            roles.add(roleService.readByName("admin"));
        }

        if (signupDTO.getRoles().contains("superadmin")){
            roles.add(roleService.readByName("superadmin"));
        }

        user.setRoles(roles);
        userRepository.save(user);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setOwner(user);
        refreshTokenRepository.save(refreshToken);

        String accessToken = jwtHelper.generateAccessToken(user);
        String refreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken.getId());

        return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody TokenDTO tokenDTO){
        String refreshTokenString = tokenDTO.getRefreshToken();
        if (jwtHelper.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString))){
            //valid and exists in DB
            refreshTokenRepository.deleteById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString));
            return ResponseEntity.ok().build();
        }
        throw new BadCredentialsException("Invalid token");
    }

    @PostMapping("/logout-all")
    public ResponseEntity<?> logoutAll(@RequestBody TokenDTO tokenDTO){
        String refreshTokenString = tokenDTO.getRefreshToken();
        if (jwtHelper.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString))){
            //valid and exists in DB
            refreshTokenRepository.deleteByOwner_Id(jwtHelper.getUserIdFromRefreshToken(refreshTokenString));
            return ResponseEntity.ok().build();
        }
        throw new BadCredentialsException("Invalid token");
    }

    @PostMapping("/access-token")
    public ResponseEntity<?> accessToken(@RequestBody TokenDTO tokenDTO){
        String refreshTokenString = tokenDTO.getRefreshToken();
        if (jwtHelper.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString))){
            //valid and exists in DB
            User user = userService.findById(jwtHelper.getUserIdFromRefreshToken(refreshTokenString));
            String accessToken = jwtHelper.generateAccessToken(user);
            return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, refreshTokenString));
        }
        throw new BadCredentialsException("Invalid token");
    }

    //Get a new refresh token and new access token
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody TokenDTO tokenDTO){
        String refreshTokenString = tokenDTO.getRefreshToken();
        if (jwtHelper.validateRefreshToken(refreshTokenString) && refreshTokenRepository.existsById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString))){
            //valid and exists in DB

            //delete incoming refresh token from db
            refreshTokenRepository.deleteById(jwtHelper.getTokenIdFromRefreshToken(refreshTokenString));


            User user = userService.findById(jwtHelper.getUserIdFromRefreshToken(refreshTokenString));

            RefreshToken refreshToken = new RefreshToken();
            refreshToken.setOwner(user);
            refreshTokenRepository.save(refreshToken);

            String accessToken = jwtHelper.generateAccessToken(user);
            String newrefreshTokenString = jwtHelper.generateRefreshToken(user, refreshToken.getId());


            return ResponseEntity.ok(new TokenDTO(user.getId(), accessToken, newrefreshTokenString));
        }
        throw new BadCredentialsException("Invalid token");
    }

}
