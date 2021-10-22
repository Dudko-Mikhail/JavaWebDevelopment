package by.bsu.shapetask.service.impl;

import by.bsu.shapetask.entity.Point;
import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.entity.TriangleTypeByAngle;
import by.bsu.shapetask.entity.TriangleTypeBySides;
import by.bsu.shapetask.service.TriangleCalculationService;
import by.bsu.shapetask.warehouse.TriangleParameters;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;

public class TriangleCalculationServiceImpl implements TriangleCalculationService { // TODO усовершенствовать путём добавления тира треугольника (прямоугольный, равнобедренный, равносторонний) ???
    private static final int SIDES_NUMBER = 3; // TODO переделать методы, использую метод нохождения длинн сторон
    private static TriangleCalculationServiceImpl INSTANCE; // TODO переделать метод computeTriangleParameters

    @Override
    public OptionalDouble computeArea(Triangle triangle) {
        if (triangle == null) {
            return OptionalDouble.empty();
        }
        double firstSide = findSideLength(triangle.getA(), triangle.getB()).getAsDouble();
        double secondSide = findSideLength(triangle.getB(), triangle.getC()).getAsDouble();
        double thirdSide = findSideLength(triangle.getA(), triangle.getC()).getAsDouble();
        double SemiPerimeter = (firstSide + secondSide + thirdSide) / 2;
        double area = Math.sqrt(SemiPerimeter * (SemiPerimeter - firstSide)
                * (SemiPerimeter - secondSide) * (SemiPerimeter - thirdSide));
        return OptionalDouble.of(area);
    }

    @Override
    public OptionalDouble computePerimeter(Triangle triangle) {
        if (triangle == null) {
            return OptionalDouble.empty();
        }
        double firstSide = findSideLength(triangle.getA(), triangle.getB()).getAsDouble();
        double secondSide = findSideLength(triangle.getB(), triangle.getC()).getAsDouble();
        double thirdSide = findSideLength(triangle.getA(), triangle.getC()).getAsDouble();
        return OptionalDouble.of(firstSide + secondSide + thirdSide);
    }

    @Override
    public OptionalDouble findSideLength(Point a, Point b) {
        if (a != null && b != null) {
            return OptionalDouble.of(Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2)));
        }
        return OptionalDouble.empty();
    }

    @Override
    public Optional<TriangleParameters> computeTriangleParameters(Triangle triangle) {
        if (triangle == null) {
            return Optional.empty();
        }

        double area = computeArea(triangle).getAsDouble();
        double perimeter = computePerimeter(triangle).getAsDouble();
        TriangleTypeByAngle typeByAngle = defineTriangleTypeByAngle(triangle).get();
        TriangleTypeBySides typeBySides = defineTriangleTypeBySides(triangle).get();
        return Optional.of(new TriangleParameters(area, perimeter, typeBySides, typeByAngle));
    }

    @Override
    public Optional<double[]> findSides(Triangle triangle) {
        if (triangle == null) {
            return Optional.empty();
        }
        double[] sides = new double[SIDES_NUMBER];
        sides[0] = findSideLength(triangle.getA(), triangle.getB()).getAsDouble();
        sides[1] = findSideLength(triangle.getB(), triangle.getC()).getAsDouble();
        sides[2] = findSideLength(triangle.getA(), triangle.getC()).getAsDouble();
        return Optional.of(sides);
    }

    @Override
    public Optional<double[]> findAnglesInDegrees(Triangle triangle) {
        if (triangle == null) {
            return Optional.empty();
        }
        double[] sides = findSides(triangle).get();
        double cosA = ((sides[0] * sides[0] + sides[1] * sides[1] - sides[2] * sides[2])) / (2 * sides[0] * sides[1]); // Теорема косинусов
        double cosB = ((sides[0] * sides[0] + sides[2] * sides[2] - sides[1] * sides[1])) / (2 * sides[0] * sides[2]);
        double[] angles = new double[SIDES_NUMBER];
        angles[0] = Math.abs(Math.acos(cosA) * 180 / Math.PI);
        angles[1] = Math.abs(Math.acos(cosB) * 180 / Math.PI);
        angles[2] = 180 - angles[0] - angles[1];
        return Optional.of(angles);
    }

    @Override
    public Optional<TriangleTypeBySides> defineTriangleTypeBySides(Triangle triangle) {
        if (triangle == null) {
            return Optional.empty();
        }
        double[] sides = findSides(triangle).get();
        int distinctSidesCount = (int) Arrays.stream(sides)
                                             .distinct()
                                             .count();
        return Optional.of(switch (distinctSidesCount) {
            case 1 -> TriangleTypeBySides.EQUILATERAL;
            case 2 -> TriangleTypeBySides.ISOSCELES;
            default -> TriangleTypeBySides.SCALENE;
        });
    }

    @Override
    public Optional<TriangleTypeByAngle> defineTriangleTypeByAngle(Triangle triangle) {
        if (triangle == null) {
            return Optional.empty();
        }
        double[] angles = findAnglesInDegrees(triangle).get();
        double maxAngle = (int) Arrays.stream(angles).max().getAsDouble();
        TriangleTypeByAngle typeByAngle;
        if (maxAngle > 90) {
            typeByAngle = TriangleTypeByAngle.OBTUSE;

        } else if (maxAngle < 90) {
            typeByAngle = TriangleTypeByAngle.ACUTE;
        } else {
            typeByAngle = TriangleTypeByAngle.RIGHT;

        }
        return Optional.of(typeByAngle);
    }

    public static TriangleCalculationServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TriangleCalculationServiceImpl();
        }
        return INSTANCE;
    }

    private TriangleCalculationServiceImpl() {
    }
}
