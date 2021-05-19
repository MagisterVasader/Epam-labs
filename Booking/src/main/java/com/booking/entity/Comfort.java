package com.booking.entity;

public enum Comfort {
    STANDARD("Стандарт"),
    LUX("Люкс"),
    VIP("VIP");

    private final String name;

    Comfort(String name) {
        this.name = name;
    }

    public Integer getId(){
        return ordinal();
    }

    public String getName(){
        return name;
    }
}
