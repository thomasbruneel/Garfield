package com.company.entities;

public class Fish {
    public int id;
    public int x;
    public int y;

    public Fish(int id,int x, int y) {
        this.id=id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Fish{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
