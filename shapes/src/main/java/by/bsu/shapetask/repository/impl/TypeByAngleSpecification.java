package by.bsu.shapetask.repository.impl;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.entity.TriangleTypeByAngle;
import by.bsu.shapetask.repository.TriangleSpecification;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.service.impl.TriangleCalculationServiceImpl;

public class TypeByAngleSpecification implements TriangleSpecification {
    private final TriangleTypeByAngle expected;

    public TypeByAngleSpecification(TriangleTypeByAngle typeByAngle) {
        expected = typeByAngle;
    }

    @Override
    public boolean test(Triangle triangle) {
        TriangleCalculationService calculationService = TriangleCalculationServiceImpl.getInstance();
        TriangleTypeByAngle actual = calculationService.defineTriangleTypeByAngle(triangle).get();
        return actual == expected;
    }
}
