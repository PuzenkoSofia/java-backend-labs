package ru.puzenko.lab3.repository;

import ru.puzenko.lab3.model.Building;
import java.util.List;
import java.util.Optional;

public interface BuildingRepository {
    int save(Building building);
    Optional<Building> findById(int id);
    List<Building> findAll();
    void update(Building building);
    void deleteById(int id);
}