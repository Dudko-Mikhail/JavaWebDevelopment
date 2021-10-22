package by.bsu.shapetask.repository.impl;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.repository.TriangleSpecification;

public class NameSpecification implements TriangleSpecification {
    private String name;

    public NameSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean test(Triangle triangle) {
        return triangle.getName().equals(name);
    }
}
