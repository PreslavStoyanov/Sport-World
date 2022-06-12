package com.example.sportworld.core.models;

public class League {
    public final int id;
    public final String name;

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
