package ru.puzenko.service;

import ru.puzenko.model.Character;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CharacterService {

    public Map<String, Integer> countBySpecies(List<Character> characters) {
        Map<String, Integer> result = new LinkedHashMap<>();

        for (Character character : characters) {
            String species = character.getSpecies();

            if (result.containsKey(species)) {
                result.put(species, result.get(species) + 1);
            } else {
                result.put(species, 1);
            }
        }

        return result;
    }
}