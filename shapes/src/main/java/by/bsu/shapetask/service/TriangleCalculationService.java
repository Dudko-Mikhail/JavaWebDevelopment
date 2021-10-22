package by.bsu.shapetask.service;

import by.bsu.shapetask.entity.Point;
import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.entity.TriangleTypeByAngle;
import by.bsu.shapetask.entity.TriangleTypeBySides;
import by.bsu.shapetask.warehouse.TriangleParameters;

import java.util.Optional;
import java.util.OptionalDouble;

public interface TriangleCalculationService {
    OptionalDouble computeArea(Triangle triangle);

    OptionalDouble computePerimeter(Triangle triangle);

    OptionalDouble findSideLength(Point a, Point b);

    Optional<double[]> findSides(Triangle triangle);

    Optional<double[]> findAnglesInDegrees(Triangle triangle);

    Optional<TriangleParameters> computeTriangleParameters(Triangle triangle);

    Optional<TriangleTypeBySides> defineTriangleTypeBySides(Triangle triangle);

    Optional<TriangleTypeByAngle> defineTriangleTypeByAngle(Triangle triangle);

}
