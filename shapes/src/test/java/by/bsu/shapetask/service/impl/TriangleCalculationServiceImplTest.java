package by.bsu.shapetask.service.impl;

import by.bsu.shapetask.entity.Point;
import by.bsu.shapetask.entity.Triangle;
import by.bsu.shapetask.entity.TriangleTypeByAngle;
import by.bsu.shapetask.entity.TriangleTypeBySides;
import by.bsu.shapetask.exception.ShapeException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.OptionalDouble;

public class TriangleCalculationServiceImplTest { // TODO написать тесты на новые методы
    private TriangleCalculationServiceImpl calculationService;
    private static final double EPSILON = 0.0001;

    @BeforeMethod
    public void initCalculationService() {
        calculationService = TriangleCalculationServiceImpl.getInstance();
    }

    @DataProvider(name = "sideLengthCorrectDataProvider")
    public Object[][] provider1() {
        return new Object[][]{
                {new Point(0,0), new Point(4, 0), 4.0},
                {new Point(0,0), new Point(0, -4), 4.0},
                {new Point(0, 3), new Point(4, 0), 5.0},
                {new Point(0, 1), new Point(1, 0), Math.sqrt(2)},
                {new Point(4, 8), new Point(4, 8), 0.0}
        };
    }

    @DataProvider(name = "sideLengthIncorrectDataProvider")
    public Object[][] provider2() {
        return new Object[][]{
                {new Point(0,0), null},
                {null, null},
                {null, new Point(1, 0)}
        };
    }

    @DataProvider(name = "triangleWithArea")
    public Object[][] provider3() throws ShapeException {
        return new Object[][]{
                {new Triangle("ABC", new Point(0,0), new Point(4, 0), new Point(0, 3)), 6.0},
                {new Triangle("ABC", new Point(0,0), new Point(6, 0), new Point(3, 4)), 12.0},
                {new Triangle("ABC", new Point(2,2), new Point(9, 9), new Point(9, 2)), 49.0/2}
        };
    }

    @DataProvider(name = "triangleWithPerimeter")
    public Object[][] provider4() throws ShapeException {
        return new Object[][]{
                {new Triangle("ABC", new Point(0,0), new Point(6, 0), new Point(0, 8)), 24.0},
                {new Triangle("ABC", new Point(0,0), new Point(6, 0), new Point(3, 4)), 16.0}
        };
    }

    @DataProvider(name = "triangleTypeBySidesProvider")
    public Object[][] provider5() throws ShapeException {
        return new Object[][] {
                {new Triangle("ABC", new Point(2,2), new Point(9, 9), new Point(9, 2)), Optional.of(TriangleTypeBySides.ISOSCELES)},
                {new Triangle("ABC", new Point(1, 3), new Point(4, -2), new Point(1, 8)), Optional.of(TriangleTypeBySides.SCALENE)},
                {new Triangle("ABC", new Point(0, 0), new Point(3, 3 * Math.sqrt(3)), new Point(6, 0)), Optional.of(TriangleTypeBySides.EQUILATERAL)},
                {null, Optional.empty()}
        };
    }

    @DataProvider(name = "triangleTypeByAngleProvider")
    public Object[][] provider6() throws ShapeException {
        return new Object[][] {
                {new Triangle("ABC", new Point(2,2), new Point(9, 9), new Point(9, 2)), Optional.of(TriangleTypeByAngle.RIGHT)},
                {new Triangle("ABC", new Point(0,0), new Point(3, 3), new Point(5, 0)), Optional.of(TriangleTypeByAngle.ACUTE)},
                {new Triangle("ABC", new Point(0,0), new Point(-2, 5), new Point(4, 0)), Optional.of(TriangleTypeByAngle.OBTUSE)},
                {null, Optional.empty()}
        };
    }

    @Test(dataProvider = "triangleWithArea")
    public void computeAreaTest(Triangle triangle, double expected) {
        double actual = calculationService.computeArea(triangle).getAsDouble();
        Assert.assertEquals(actual, expected, EPSILON);
    }

    @Test(dataProvider = "triangleWithPerimeter")
    public void computePerimeterTest(Triangle triangle, double expected) {
        double actual = calculationService.computePerimeter(triangle).getAsDouble();
        Assert.assertEquals(actual, expected, EPSILON);
    }

    @Test(dataProvider = "sideLengthCorrectDataProvider")
    public void findSideLengthWithCorrectDataTest(Point a, Point b, double expected) {
        double actual = calculationService.findSideLength(a, b).getAsDouble();
        Assert.assertEquals(actual, expected, EPSILON);
    }

    @Test(dataProvider = "sideLengthIncorrectDataProvider")
    public void findSideLengthWithIncorrectDataTest(Point a, Point b) {
        OptionalDouble actual = calculationService.findSideLength(a, b);
        OptionalDouble expected = OptionalDouble.empty();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "triangleTypeBySidesProvider")
    public void  defineTriangleTypeBySidesTest(Triangle triangle, Optional<TriangleTypeBySides> expected) {
        Optional<TriangleTypeBySides> actual = calculationService.defineTriangleTypeBySides(triangle);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "triangleTypeByAngleProvider")
    public void defineTriangleTypeByAngleTest(Triangle triangle, Optional<TriangleTypeByAngle> expected) {
        Optional<TriangleTypeByAngle> actual = calculationService.defineTriangleTypeByAngle(triangle);
        Assert.assertEquals(actual, expected);

    }
}
