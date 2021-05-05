package com.booking.entity;

import java.util.Date;

public class Order extends Entity{
    private Integer roomId;
    private Comfort comfort;
    private Integer capacity;
    private Date durationStart;
    private Date durationEnd;
    private Integer userId;

    public Comfort getComfort() {
        return comfort;
    }

    public void setComfort(Comfort comfort) {
        this.comfort = comfort;
    }

    public Date getDurationStart() {
        return durationStart;
    }

    public void setDurationStart(Date durationStart) {
        this.durationStart = durationStart;
    }

    public Date getDurationEnd() {
        return durationEnd;
    }

    public void setDurationEnd(Date durationEnd) {
        this.durationEnd = durationEnd;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "roomId=" + roomId +
                ", comfort=" + comfort +
                ", capacity=" + capacity +
                ", durationStart=" + durationStart +
                ", durationEnd=" + durationEnd +
                '}';
    }
}
