package com.booking.entity;

public class Room extends Entity{
    private Comfort comfort;
    private Integer price;
    private Boolean free;
    private Integer capacity;

    public Comfort getComfort() {
        return comfort;
    }

    public void setComfort(Comfort comfort) {
        this.comfort = comfort;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getFree() {
        return free;
    }

    public void setFree(Boolean free) {
        this.free = free;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "comfort=" + comfort +
                ", price=" + price +
                ", free=" + free +
                ", capacity=" + capacity +
                '}';
    }
}
