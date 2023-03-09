package com.rafael.app.blogru.document;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Document
@Data
public class User implements UserDetails {

    @Id
    private String id;

    @Indexed(unique = true)
    @NonNull
    private String username;

    @Indexed(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String password;

    @DocumentReference
    private Set<Role> roles;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    @Transient
    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }





    //Se debe devolver esta clase con los roles en authorities
    /*
    public static MainUserEntity build(UserEntity userEntity) {
        //1. Obtener los roles
        //2. Convertirlos a authorities
        //(Se convierte la clase rol a la clase GrantedAuthority)
        Set<RoleEntity> rolesUsuario = userEntity.getRoles();
        List<GrantedAuthority> authorities = rolesUsuario.stream()
                .map(rol->new SimpleGrantedAuthority(rol.getRolNombre().name()))
                .collect(Collectors.toList());

        //FormaSimplificada:
        //List<GrantedAuthority> authorities = usuarioModel.getRoles()stream().map(rol->new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());;

        return new MainUserEntity(userEntity.getNombre(),
                userEntity.getNombreUsuario(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                authorities);
    }
    */
    public static User build(User userSource){
        //1. Get roles
        //2. Convert into authorities
        Set<Role> roles = userSource.getRoles();
        List<GrantedAuthority> authorities = roles.stream()
                .map( rol -> new SimpleGrantedAuthority(rol.getName()))
                .collect(Collectors.toList());

        return new User(
                userSource.getUsername(),
                userSource.getEmail(),
                userSource.getPassword(),
                authorities
        );
    }
}
