package by.bsu.firsttask.service.impl;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class IntStreamCalculationServiceTest {
    private IntStreamCalculationServiceImpl calculationService;

    @BeforeMethod
    public void initCalculationService() {
        calculationService = new IntStreamCalculationServiceImpl();
    }

    @Test
    public void findMinTest() {
        CustomArray array = new CustomArray(-8, 32, 45, 72);
        OptionalInt expected = OptionalInt.of(-8);
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
        CustomArray array = new CustomArray(6, -9, 45, 2, 1000, -4, 0);
        OptionalInt expected = OptionalInt.of(1000);
        OptionalInt actual = calculationService.findMax(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findAverageTest() {
        CustomArray array = new CustomArray(1, 2, 3);
        OptionalDouble expected = OptionalDouble.of(2);
        OptionalDouble actual = calculationService.findAverage(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findSumTest() {
        CustomArray array = new CustomArray(1, 2, 7);
        int expected = 10;
        int actual = calculationService.findSum(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void countPositiveNumbersTest() {
        CustomArray array = new CustomArray(8, 50, 14, 13, -9, 0, 0, 0);
        int expected = 4;
        int actual = calculationService.countPositiveNumbers(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void countNegativeNumbersTest() {
        CustomArray array = new CustomArray(9, 45, -98, 8);
        int expected = 1;
        int actual = calculationService.countNegativeNumbers(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void multiplyByNumberTest() throws CustomArrayException {
        CustomArray actual = new CustomArray(6, 7, 96, -45, 0);
        CustomArray expected = new CustomArray(new int[5]);
        calculationService.multiplyByNumber(actual, 0);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomArrayException.class)
    public void multiplyByNumberExceptionTest() throws CustomArrayException {
        calculationService.multiplyByNumber(null, 0);
    }
}
