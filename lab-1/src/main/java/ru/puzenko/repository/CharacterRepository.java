package ru.puzenko.repository;

import ru.puzenko.model.Character;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CharacterRepository {

    public List<Character> readCharactersFromCsv(String fileName) {
        List<Character> characters = new ArrayList<>();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader br = new BufferedReader(new InputStreamReader(Objects.requireNonNull(is)))) {

            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");

                // species находится в колонке с индексом 3
                if (parts.length > 3) {
                    String name = parts[1].trim();
                    String species = parts[3].trim();
                    characters.add(new Character(name, species));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + fileName, e);
        }

        return characters;
    }
}