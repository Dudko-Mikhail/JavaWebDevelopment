package by.bsu.shapetask.repository.impl;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.repository.TriangleSpecification;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.service.impl.TriangleCalculationServiceImpl;

public class AreaSpecification implements TriangleSpecification {
    private double minArea;
    private double maxArea;

    public AreaSpecification(double firstBound, double secondBound) {
        this.minArea = Math.min(firstBound, secondBound);
        this.maxArea = Math.max(firstBound, secondBound);
    }

    @Override
    public boolean test(Triangle triangle) {
        TriangleCalculationService calculationService = TriangleCalculationServiceImpl.getInstance();
        double triangleArea = calculationService.computeArea(triangle).getAsDouble();
        return Double.compare(triangleArea, minArea) >= 0 && Double.compare(triangleArea, maxArea) <= 0;
    }
}
