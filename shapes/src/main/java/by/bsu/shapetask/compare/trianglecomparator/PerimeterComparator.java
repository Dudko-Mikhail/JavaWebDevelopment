package by.bsu.shapetask.compare.trianglecomparator;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.service.impl.TriangleCalculationServiceImpl;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Triangle> {
    @Override
    public int compare(Triangle o1, Triangle o2) {
        TriangleCalculationService calculationService = TriangleCalculationServiceImpl.getInstance();
        double perimeter1 = calculationService.computePerimeter(o1).getAsDouble();
        double perimeter2 = calculationService.computePerimeter(o2).getAsDouble();
        return Double.compare(perimeter1, perimeter2);
    }
}
