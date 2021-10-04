package by.bsu.firsttask.utils;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class IntValidatorTest {

    @DataProvider(name = "provider")
    public Object[][] getData() {
        return new Object[][] {
                {"-9", true},
                {"0", true},
                {"+94", true},
                {"915511", true},
                {"-9d4", false},
                {null, false},
                {"--9", false},
                {"++45", false},
                {"+-19", false},
                {"-+7", false},
                {"5.7", false},
        };
    }

    @Test(dataProvider = "provider")
    public void isValidTest(String number, boolean expected) {
        boolean actual = IntValidator.isValid(number);
        Assert.assertEquals(actual, expected);
    }

}
