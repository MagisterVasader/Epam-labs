package com.booking.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable{
    private Integer id;

    protected Entity(){}

    protected Entity(Integer id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
