package ru.puzenko.lab2.repository;

import ru.puzenko.lab2.entity.Building;
import java.util.List;

public interface BuildingRepository {
    int save(Building building);
    Building findById(int id);
    Building findByAddress(String address);
    List<Building> findAll();
    boolean update(Building building);
    void deleteById(int id);
}