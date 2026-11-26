package ru.puzenko.lab3.service;

import ru.puzenko.lab3.model.Building;
import java.util.List;

public interface BuildingService {
    int save(Building building);
    Building findById(int id);
    List<Building> findAll();
    void update(Building building);
    void deleteById(int id);
}