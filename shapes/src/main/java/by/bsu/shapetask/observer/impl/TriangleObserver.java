package by.bsu.shapetask.observer.impl;

import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.observer.Observer;
import by.bsu.shapetask.observer.TriangleEvent;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.service.impl.TriangleCalculationServiceImpl;
import by.bsu.shapetask.warehouse.TriangleParameters;
import by.bsu.shapetask.warehouse.Warehouse;

public class TriangleObserver implements Observer {
    public void parameterChanged(TriangleEvent event) {
        Triangle source = event.getSource();
        TriangleCalculationService calculationService = TriangleCalculationServiceImpl.getInstance();
        TriangleParameters newParameters = calculationService.computeTriangleParameters(source).get();
        Warehouse warehouse = Warehouse.getInstance();
        warehouse.putTriangleParameters(source.getShapeId(), newParameters);
    }
}
