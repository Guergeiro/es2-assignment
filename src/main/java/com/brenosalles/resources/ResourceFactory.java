package com.brenosalles.resources;

public abstract class ResourceFactory {
    public static Resource createResource(Integer id, String name, Integer year, String color, String pantoneValue)
            throws InvalidResourceException {
        if (validateName(name) == false) {
            throw new InvalidResourceException();
        }
        if (validateYear(year) == false) {
            throw new InvalidResourceException();
        }
        if (validateColor(color) == false) {
            throw new InvalidResourceException();
        }
        if (validatePantoneColor(pantoneValue) == false) {
            throw new InvalidResourceException();
        }
        if (id != null) {
            return new Resource(id, name, year, color, pantoneValue);
        }
        return new Resource(name, year, color, pantoneValue);
    }

    private static Boolean validateName(String name) {
        if (name == null) {
            return false;
        }
        if (name.length() < 4) {
            return false;
        }
        if (name.length() > 16) {
            return false;
        }
        return true;
    }

    private static Boolean validateYear(Integer year) {
        return year != null;
    }

    private static Boolean validateColor(String color) {
        if (color == null) {
            return false;
        }

        return color.matches("^#.{6}$");
    }

    private static Boolean validatePantoneColor(String pantoneValue) {
        return pantoneValue != null;
    }
}