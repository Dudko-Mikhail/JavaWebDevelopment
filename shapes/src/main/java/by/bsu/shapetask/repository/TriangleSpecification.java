package by.bsu.shapetask.repository;

import by.bsu.shapetask.entity.Triangle;

import java.util.function.Predicate;

@FunctionalInterface
public interface TriangleSpecification extends Predicate<Triangle> {

    boolean test(Triangle triangle);
}
