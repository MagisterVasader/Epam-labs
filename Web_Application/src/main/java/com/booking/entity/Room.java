package com.booking.entity;

public class Room extends Entity{
    private Integer roomId;
    private Comfort comfort;
    private Integer price;
    private Free free;
    private Integer capacity;

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

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

    public Free getFree() {
        return free;
    }

    public void setFree(Free free) {
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
                "roomId=" + roomId +
                ", comfort=" + comfort +
                ", price=" + price +
                ", free=" + free +
                ", capacity=" + capacity +
                '}';
    }
}
