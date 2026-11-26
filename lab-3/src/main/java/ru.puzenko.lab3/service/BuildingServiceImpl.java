package ru.puzenko.lab3.service;

import org.springframework.stereotype.Service;
import ru.puzenko.lab3.exception.BuildingNotFoundException;
import ru.puzenko.lab3.model.Building;
import ru.puzenko.lab3.repository.BuildingRepository;
import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
    }

    @Override
    public int save(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public Building findById(int id) {
        return buildingRepository.findById(id)
                .orElseThrow(() -> new BuildingNotFoundException("Building with id " + id + " not found"));
    }

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public void update(Building building) {
        findById(building.getId());
        buildingRepository.update(building);
    }

    @Override
    public void deleteById(int id) {
        buildingRepository.deleteById(id);
    }
}