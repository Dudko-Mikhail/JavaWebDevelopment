package by.bsu.shapetask.repository.impl;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.repository.TriangleSpecification;

import java.util.UUID;

public class IdSpecification implements TriangleSpecification {
    private UUID id;

    private IdSpecification(UUID id) {
        this.id = id;
    }

    @Override
    public boolean test(Triangle triangle) {
        return triangle.getShapeId().equals(id);
    }
}
