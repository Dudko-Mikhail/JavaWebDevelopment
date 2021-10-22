package by.bsu.shapetask.factory;

import by.bsu.shapetask.entity.Point;
import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.exception.ShapeException;

public class TriangleFactory {
    private static TriangleFactory instance;

    public static TriangleFactory getInstance() {
        if (instance == null) {
            instance = new TriangleFactory();
        }
        return instance;
    }

    public Triangle getTriangle(String name, Point a, Point b, Point c) throws ShapeException {
        return new Triangle(name, a, b, c);
    }

    private TriangleFactory() {};
}
