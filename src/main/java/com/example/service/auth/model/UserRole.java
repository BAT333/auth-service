package com.example.service.auth.model;

public enum UserRole {
    ADMIN("admin"),
    SUB_ADMIN("sub_admin"),
    USER("user"),
    SUB("sub");
    private final String values;
   UserRole(String values){
        this.values = values;
    }

    public String getValues() {
        return values;
    }
}
