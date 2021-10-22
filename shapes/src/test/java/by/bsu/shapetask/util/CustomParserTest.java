package by.bsu.shapetask.util;

import by.bsu.shapetask.exception.CustomReaderException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CustomParserTest {
    private CustomParser parser;

    @BeforeMethod
    public void initParser() {
        parser = CustomParser.getInstance();
    }

    @DataProvider(name = "correctDataProvider")
    public Object[][] provideCorrectData() {
        return new Object[][] {
                {"2.3 8.5 9.5 4.1 5.9 9.5", new double[] {2.3, 8.5, 9.5, 4.1, 5.9, 9.5}},
                {"+69 -52 23 4.5 9.6 8", new double[] {69, -52, 23, 4.5, 9.6, 8}},
                {"6.9 13 23 -4.5 9.6 -8.8", new double[] {6.9, 13, 23, -4.5, 9.6, -8.8}},
        };
    }

    @DataProvider(name = "invalidDataProvider")
    public Object[][] provideInvalidData() {
        return new Object[][] {
                {null},
                {"4.2 4.5 7.4 2.3 5.7"},
                {"1.2 5.3 1 6 8 2 7"},
                {"9.4, 7 5 9 6 3"},
        };
    }

    @Test(dataProvider = "correctDataProvider")
    public void parseLineTestWithCorrectData(String line, double[] expected) throws CustomReaderException {
        double[] actual = parser.parseLine(line);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "invalidDataProvider", expectedExceptions = CustomReaderException.class)
    public void parseLineTestWithIncorrectData(String line) throws CustomReaderException {
        parser.parseLine(line);
    }
}
