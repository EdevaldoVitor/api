package com.desafio.datahub.api.dto;

import com.desafio.datahub.api.model.UserRole;

public record RegisterDTO(String email, String nome, String senha, UserRole role) {
}
