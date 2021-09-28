package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import org.junit.Assert;
import org.junit.Test;

public class MultiplierTest {
    @Test
    public void multiplyTest() {
        double a = 13.3;
        double b = 2;
        CustomNumber first = new CustomNumber(a);
        CustomNumber second = new CustomNumber(b);
        double givenResult = Multiplier.multiply(first, second).getNumber();
        Assert.assertEquals(a * b, givenResult, 0.000001);
    }

    @Test
    public void multiplyByZeroTest() {
        CustomNumber first = new CustomNumber(13.7);
        CustomNumber second = new CustomNumber(3.4);
        CustomNumber third = new CustomNumber(0);
        double givenResult = Multiplier.multiply(first, second, third).getNumber();
        Assert.assertEquals(0, givenResult, 0.00000001);
    }
}
