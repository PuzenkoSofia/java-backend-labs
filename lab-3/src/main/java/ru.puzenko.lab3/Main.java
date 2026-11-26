package ru.puzenko.lab3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.puzenko.lab3.model.Building;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = "ru.puzenko.lab3")
public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.classformat.ignore", "true");
        SpringApplication.run(Main.class, args);
    }

    @RestController
    public class TempController {
        @GetMapping("/api/buildings")
        public List<Building> getAll() {
            return List.of(new Building(1, "Test Building", 5));
        }
    }
}