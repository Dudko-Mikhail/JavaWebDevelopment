package by.bsu.firsttask.service.impl;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class CalculationServiceImplTest {
    private CalculationServiceImpl calculationService;

    @BeforeMethod
    public void initCalculationService() {
        calculationService = new CalculationServiceImpl();
    }

    @Test
    public void findMinTest() {
        CustomArray array = new CustomArray(4, 9, 5, 74, 11, -2);
        OptionalInt expected = OptionalInt.of(-2);
        OptionalInt actual = calculationService.findMin(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findMinNullTest() {
        OptionalInt expected = OptionalInt.empty();
        OptionalInt actual = calculationService.findMin(null);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findMaxTest() {
        CustomArray array = new CustomArray(-2, 17, 20, 38, 19, 90, 47, 28, 37);
        OptionalInt expected = OptionalInt.of(90);
        OptionalInt actual = calculationService.findMax(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findAverageTest() {
        CustomArray array = new CustomArray(3, 6, 9);
        OptionalDouble expected = OptionalDouble.of(6);
        OptionalDouble actual = calculationService.findAverage(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findSumTest() {
        CustomArray array = new CustomArray(30, 28, -8);
        int expected = 50;
        int actual = calculationService.findSum(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void countPositiveNumbersTest() {
        CustomArray array = new CustomArray(-9, 45, 20, -48, 19, 0, 0);
        int expected = 3;
        int actual = calculationService.countPositiveNumbers(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void countNegativeNumbersTest() {
        CustomArray array = new CustomArray(-0, -3, 8, 78, -2, 0);
        int expected = 2;
        int actual = calculationService.countNegativeNumbers(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void multiplyByNumberTest() throws CustomArrayException {
        CustomArray actual = new CustomArray(9, 45, -9, 5, 6);
        CustomArray expected = new CustomArray(new int[5]);
        calculationService.multiplyByNumber(actual, 0);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomArrayException.class)
    public void multiplyByNumberExceptionTest() throws CustomArrayException {
        calculationService.multiplyByNumber(null, 0);
    }
}
