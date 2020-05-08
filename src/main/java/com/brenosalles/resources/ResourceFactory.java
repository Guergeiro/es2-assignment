package com.brenosalles.resources;

public abstract class ResourceFactory {
    public static Resource createResource(Integer id, String name, Integer year, String color, String pantoneValue) {
        if (id != null) {
            return new Resource(id, name, year, color, pantoneValue);
        }
        return new Resource(name, year, color, pantoneValue);
    }
}