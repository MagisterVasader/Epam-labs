package com.booking.entity;

public enum Comfort {
    STANDARD("comfort.STANDARD"),
    LUX("comfort.LUX"),
    VIP("comfort.VIP");

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

    @Override
    public String toString() {
        return name.split("\\.")[1];
    }
}
