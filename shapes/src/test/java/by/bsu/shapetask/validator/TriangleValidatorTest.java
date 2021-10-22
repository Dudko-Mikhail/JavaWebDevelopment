package by.bsu.shapetask.validator;

import by.bsu.shapetask.entity.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TriangleValidatorTest {
    TriangleValidator validator;

    @BeforeMethod
    public void initValidator() {
        validator = TriangleValidator.getInstance();
    }

    @DataProvider(name = "provider")
    public Object[][] provide() {
        return new Object[][] {
                {new Point(0, 0), null, new Point(0, 3), false},
                {new Point(0, 0), new Point(1, 1), new Point(5.2, 5.2), false},
                {new Point(0, 0), new Point(4, 0), new Point(0, 3), true},
                {new Point(1, 3), new Point(4, 6), new Point(-2, 8), true}
        };
    }

    @Test(dataProvider = "provider")
    public void isValidTest(Point a, Point b, Point c, boolean expected) {
        boolean actual = validator.isValid(a, b, c);
        Assert.assertEquals(actual, expected);
    }
}
