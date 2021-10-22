package by.bsu.shapetask.repository.impl;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.entity.TriangleTypeBySides;
import by.bsu.shapetask.repository.TriangleSpecification;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.service.impl.TriangleCalculationServiceImpl;

public class TypeBySidesSpecification implements TriangleSpecification {
    private final TriangleTypeBySides expected;

    public TypeBySidesSpecification(TriangleTypeBySides typeBySides) {
        expected = typeBySides;
    }

    @Override
    public boolean test(Triangle triangle) {
        TriangleCalculationService calculationService = TriangleCalculationServiceImpl.getInstance();
        TriangleTypeBySides actual = calculationService.defineTriangleTypeBySides(triangle).get();
        return actual == expected;
    }
}
