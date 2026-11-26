package ru.puzenko.lab3.model;

public class Building {
    private int id;
    private String address;
    private int floors;

    public Building() {
    }

    public Building(int id, String address, int floors) {
        this.id = id;
        this.address = address;
        this.floors = floors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }
}