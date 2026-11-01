package ru.puzenko.lab2;

import ru.puzenko.lab2.entity.Building;
import ru.puzenko.lab2.repository.BuildingRepositoryJdbc;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bd_lab2";
        String user = "lab2user";
        String password = "sonita2005alisa2009";

        BuildingRepositoryJdbc repo = new BuildingRepositoryJdbc(url, user, password);

        repo.createTableIfNotExists();

        System.out.println("\n--- CREATE ---");
        Building b = new Building(null, "ул. Ленина, 10", 5);
        int id = repo.save(b);
        System.out.println("Сохранено с id=" + id);

        System.out.println("\n--- READ (findById) ---");
        System.out.println(repo.findById(id));

        System.out.println("\n--- READ (findByAddress) ---");
        System.out.println(repo.findByAddress("ул. Ленина, 10"));

        System.out.println("\n--- READ (findAll) ---");
        System.out.println(repo.findAll());

        System.out.println("\n--- UPDATE ---");
        b.setFloors(10);
        System.out.println("Обновлено: " + repo.update(b));
        System.out.println("После: " + repo.findById(id));

        System.out.println("\n--- DELETE ---");
        repo.deleteById(id);
        System.out.println("После удаления: " + repo.findById(id));
    }
}