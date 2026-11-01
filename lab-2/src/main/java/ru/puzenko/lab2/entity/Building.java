package ru.puzenko.lab2.entity;

public class Building {
    private Integer id;
    private String address;
    private int floors;

    public Building(Integer id, String address, int floors) {
        this.id = id;
        this.address = address;
        this.floors = floors;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public int getFloors() { return floors; }
    public void setFloors(int floors) { this.floors = floors; }

    @Override
    public String toString() {
        return "Building{id=" + id + ", address='" + address + "', floors=" + floors + '}';
    }
}