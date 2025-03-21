package com.desafio.datahub.api.repository;

import com.desafio.datahub.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);

}
