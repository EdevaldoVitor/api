package com.desafio.datahub.api.model;

public enum UserRole {

    ADMIN("admin"),

    USER("user");

    private String role;

    UserRole(String role){
        this.role = role;
    }
    public String getPermissao(){
        return role;
    }

}
