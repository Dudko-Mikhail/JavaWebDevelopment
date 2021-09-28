package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import org.junit.Assert;
import org.junit.Test;

public class AdderTest {
    @Test
    public void addTest() {
        double a = 10.6;
        double b = 19.4;
        CustomNumber first = new CustomNumber(a);
        CustomNumber second = new CustomNumber(b);
        double givenResult = Adder.add(first, second).getNumber();
        Assert.assertEquals(a + b, givenResult, 0.000001);
    }
}
