package ru.puzenko.lab3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.puzenko.lab3.model.Building;
import ru.puzenko.lab3.service.BuildingService;
import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @PostMapping
    public ResponseEntity<Integer> createBuilding(@RequestBody Building building) {
        int id = buildingService.save(building);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable int id) {
        return buildingService.findById(id);
    }

    @GetMapping
    public List<Building> getAllBuildings() {
        return buildingService.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBuilding(@PathVariable int id, @RequestBody Building building) {
        building.setId(id);
        buildingService.update(building);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable int id) {
        buildingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}