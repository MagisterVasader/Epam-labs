package com.booking.entity;

public enum Comfort {
    STANDARD("comfort.standard"),
    LUX("comfort.lux"),
    VIP("comfort.vip");

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
