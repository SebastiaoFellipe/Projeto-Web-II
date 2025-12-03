package com.bti.projetoweb2.users;

public enum UserRole {
    ADMIN("admin"),
    CANDIDATO("candidato"),
    PROFESSOR("professor"),
    FUNCIONARIO_COMUM("funcionario");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
