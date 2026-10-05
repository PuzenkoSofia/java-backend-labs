package ru.puzenko;

import ru.puzenko.model.Character;
import ru.puzenko.repository.CharacterRepository;
import ru.puzenko.service.CharacterService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        CharacterRepository repository = new CharacterRepository();
        List<Character> characters = repository.readCharactersFromCsv("characters.csv");

        CharacterService service = new CharacterService();
        Map<String, Integer> result = service.countBySpecies(characters);

        writeResultToFile(result, "result.csv");

        System.out.println("Готово! Результат записан в result.csv");
    }

    private static void writeResultToFile(Map<String, Integer> data, String outputFileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write("species,count");
            writer.newLine();

            for (Map.Entry<String, Integer> entry : data.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл: " + outputFileName, e);
        }
    }
}