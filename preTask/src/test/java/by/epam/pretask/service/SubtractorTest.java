package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import org.junit.Assert;
import org.junit.Test;

public class SubtractorTest {
    @Test
    public void subtractTest() {
        double a = 19.9;
        double b = 19.5;
        CustomNumber first = new CustomNumber(a);
        CustomNumber second = new CustomNumber(b);
        double givenResult = Subtractor.subtract(first, second).getNumber();
        Assert.assertEquals(a - b, givenResult, 0.000001);
    }
}
