package com.booking.entity;

public enum Comfort {
    STANDARD("STANDARD"),
    LUX("LUX"),
    VIP("VIP");

    private final String name;

    Comfort(String name) {
        this.name = name;
    }

    public Integer getId() {
        return ordinal();
    }

    public String getName() {
        return name;
    }
}
