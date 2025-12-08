package com.bti.projetoweb2.users;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {
    ADMIN("admin"),
    CANDIDATO("candidato"),
    PROFESSOR("professor"),
    FUNCIONARIO_COMUM("funcionario");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static UserRole fromString(String text) {
        for (UserRole b : UserRole.values()) {
            if (b.role.equalsIgnoreCase(text) || b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Nenhuma role encontrada para: " + text);
    }
}
