package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import org.junit.Assert;
import org.junit.Test;

public class DividerTest {
    @Test
    public void divisionTest() {
        double a = 13.3;
        double b = 2;
        CustomNumber first = new CustomNumber(a);
        CustomNumber second = new CustomNumber(b);
        double givenResult = Divider.divide(first, second).getNumber();
        Assert.assertEquals(a / b, givenResult, 0.000001);
    }

    @Test
    public void PositiveInfinityTest() {
        CustomNumber first = new CustomNumber(13.7);
        CustomNumber second = new CustomNumber(0);
        double givenResult = Divider.divide(first, second).getNumber();
        Assert.assertEquals(Double.POSITIVE_INFINITY, givenResult, 0.00000001);
    }

    @Test
    public void NegativeInfinityTest() {
        CustomNumber first = new CustomNumber(-1.7);
        CustomNumber second = new CustomNumber(0);
        double givenResult = Divider.divide(first, second).getNumber();
        Assert.assertEquals(Double.NEGATIVE_INFINITY, givenResult, 0.00000001);
    }

    @Test
    public void divideZeroByZeroTest() {
        CustomNumber first = new CustomNumber(0);
        CustomNumber second = new CustomNumber(0);
        double givenResult = Divider.divide(first, second).getNumber();
        Assert.assertEquals(Double.NaN, givenResult, 0.00000001);
    }
}
