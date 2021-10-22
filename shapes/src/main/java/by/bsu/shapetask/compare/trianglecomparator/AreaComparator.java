package by.bsu.shapetask.compare.trianglecomparator;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.service.impl.TriangleCalculationServiceImpl;

import java.util.Comparator;

public class AreaComparator implements Comparator<Triangle> {
    @Override
    public int compare(Triangle o1, Triangle o2) {
        TriangleCalculationService calculationService = TriangleCalculationServiceImpl.getInstance();
        double area1 = calculationService.computeArea(o1).getAsDouble();
        double area2 = calculationService.computeArea(o2).getAsDouble();
        return Double.compare(area1, area2);
    }

}
