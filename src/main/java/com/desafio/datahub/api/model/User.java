package com.desafio.datahub.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")
@Table(name = "usuario")
public class User implements UserDetails {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "email", columnDefinition = "TEXT")
   private String email;

   @Column(name = "nome", columnDefinition = "TEXT")
   private String nome;

   @Column(name = "senha", columnDefinition = "TEXT")
   private String senha;

   @Enumerated(EnumType.STRING)
   private UserRole role;

   public User (String email, String nome, String senha, UserRole role){
      this.email = email;
      this.nome = nome;
      this.senha = senha;
      this.role = role;
   }



   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      if (this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
      else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
   }

   @Override
   public String getPassword() {
      return senha;
   }

   @Override
   public String getUsername() {
      return email;
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
}
