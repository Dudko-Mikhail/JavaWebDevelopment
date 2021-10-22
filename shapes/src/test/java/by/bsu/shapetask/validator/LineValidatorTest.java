package by.bsu.shapetask.validator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LineValidatorTest {
    private LineValidator validator;

    @BeforeMethod
    public void initValidator() {
        validator = LineValidator.getInstance();
    }

    @DataProvider(name = "textProvider")
    public Object[][] provider() {
        return new Object[][] {
                {null, false},
                {"4.2 4.5 7.4 2.3 5.7", false},
                {"1.2 5.3 1 6 8 2 7", false},
                {"9.4, 7 5 9 6 3", false},
                {"2.3 8.5 9.5 4.1 5.9 9.5", true},
                {"+69 -52 23 4.5 9.6 8", true},
        };
    }

    @Test(dataProvider = "textProvider")
    public void validateLineTest(String data, boolean expected) {
        boolean actual = validator.validateLine(data);
        Assert.assertEquals(actual, expected);
    }
}
