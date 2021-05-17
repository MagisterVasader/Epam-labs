package com.booking.entity;

import java.sql.Date;

public class Order extends Entity{
    private Integer roomId;
    private Date durationStart;
    private Date durationEnd;
    private Integer userId;

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
                ", durationStart=" + durationStart +
                ", durationEnd=" + durationEnd +
                ", userId=" + userId +
                '}';
    }
}
