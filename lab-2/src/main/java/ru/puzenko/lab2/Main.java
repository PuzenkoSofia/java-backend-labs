package ru.puzenko.lab2;

import ru.puzenko.lab2.entity.Building;
import ru.puzenko.lab2.repository.JdbcBuildingRepository;

import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (is == null) {
                throw new RuntimeException("Файл database.properties не найден в resources!");
            }
            props.load(is);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при чтении настроек БД", e);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        JdbcBuildingRepository repository = new JdbcBuildingRepository(url, user, password);

        repository.createTableIfNotExists();

        System.out.println("\n--- CREATE ---");
        Building newBuilding = new Building(null, "ул. Ленина, 10", 5);
        int id = repository.save(newBuilding);
        System.out.println("Сохранено здание с id: " + id);

        System.out.println("\n--- READ (findById) ---");
        Building foundById = repository.findById(id);
        System.out.println("Найдено: " + foundById);

        System.out.println("\n--- READ (findByAddress) ---");
        Building foundByAddress = repository.findByAddress("ул. Ленина, 10");
        System.out.println("Найдено по адресу: " + foundByAddress);

        System.out.println("\n--- READ (findAll) ---");
        System.out.println("Все здания: " + repository.findAll());

        System.out.println("\n--- UPDATE ---");
        foundById.setFloors(10);
        boolean updated = repository.update(foundById);
        System.out.println("Обновлено? " + updated);
        System.out.println("После обновления: " + repository.findById(id));

        System.out.println("\n--- DELETE ---");
        repository.deleteById(id);
        System.out.println("Удалено. Проверка: " + repository.findById(id));
    }
}