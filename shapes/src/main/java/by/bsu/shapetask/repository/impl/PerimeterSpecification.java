package by.bsu.shapetask.repository.impl;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.repository.TriangleSpecification;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.service.impl.TriangleCalculationServiceImpl;

public class PerimeterSpecification implements TriangleSpecification {
    private double minPerimeter;
    private double maxPerimeter;

    public PerimeterSpecification(double firstBound, double secondBound) {
        this.minPerimeter = Math.min(firstBound, secondBound);
        this.maxPerimeter = Math.max(firstBound, secondBound);
    }

    @Override
    public boolean test(Triangle triangle) {
        TriangleCalculationService calculationService = TriangleCalculationServiceImpl.getInstance();
        double trianglePerimeter = calculationService.computePerimeter(triangle).getAsDouble();
        return Double.compare(trianglePerimeter, minPerimeter) >= 0
                && Double.compare(trianglePerimeter, maxPerimeter) <= 0;
    }
}
