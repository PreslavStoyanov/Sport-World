package com.sportworld.core.models;

public class League {
    public int id;
    public String name;

    public League() {
    }

    public League(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "League{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
