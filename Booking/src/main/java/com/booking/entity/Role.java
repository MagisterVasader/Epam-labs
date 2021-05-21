package com.booking.entity;

public enum Role {
    ADMIN("ADMIN"),
    CLIENT("CLIENT");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
