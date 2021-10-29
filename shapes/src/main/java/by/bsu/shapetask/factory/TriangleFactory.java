package by.bsu.shapetask.factory;

import by.bsu.shapetask.entity.Point;
import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.exception.ShapeException;

public class TriangleFactory {
    private static TriangleFactory instance;

    public static TriangleFactory getInstance() { // TODO удалить 1 из методов
        if (instance == null) {
            instance = new TriangleFactory();
        }
        return instance;
    }

    public Triangle createTriangle(String name, Point a, Point b, Point c) throws ShapeException {
        return new Triangle(name, a, b, c);
    }

    public Triangle createTriangle(String name, double x1, double y1, double x2,
                                   double y2, double x3, double y3) throws ShapeException {
        Point a = new Point(x1, y1);
        Point b = new Point(x2, y2);
        Point c = new Point(x3, y3);
        return new Triangle(name, a, b, c);
    }

    private TriangleFactory() {};
}
