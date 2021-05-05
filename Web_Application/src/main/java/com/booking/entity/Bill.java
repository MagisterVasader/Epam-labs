package com.booking.entity;

public class Bill extends Entity {
    private int totalPrice;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "totalPrice=" + totalPrice +
                '}';
    }
}
