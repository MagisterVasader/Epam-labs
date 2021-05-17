package com.booking.entity;

public enum Role {
    ADMIN("role.ADMIN"),
    CLIENT("role.CLIENT");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public Integer getId(){
        return ordinal();
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return name.split("\\.")[1];
    }
}
