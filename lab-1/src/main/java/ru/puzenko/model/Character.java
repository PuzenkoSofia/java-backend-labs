package ru.puzenko.model;

public class Character {
    private String name;
    private String species;

    public Character(String name, String species) {
        this.name = name;
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }
}