package by.bsu.firsttask.utils;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomParserTest {
    private CustomParser parser;

    @BeforeMethod
    public void initParser() {
        parser = new CustomParser();
    }

    @DataProvider(name = "provider")
    public Object[][] getData() {
        return new Object[][] {
                {new String[]{"  34 78    89    12  ", "  -28, .78 45"}, new Integer[]{34, 78, 89, 12, 45}},
                {new String[]{" -34  +52  5.3  +98  +-982 24  95"}, new Integer[]{-34, 52, 98, 24, 95}},
                {new String[]{" -34,  +52  5.3  +98,  +-982 24  95"}, new Integer[]{52, 24, 95}},
                {new String[]{" -34  52 5.3  +98 24  95", null, "25, 65", null}, new Integer[]{-34, 52, 98, 24, 95, 65}}
        };
    }

    @Test(dataProvider = "provider")
    public void parseTest(String lines[], Integer[] resultArray) {
        ArrayList<String> strings = new ArrayList<>(Arrays.asList(lines));
        Integer[] numbers = parser.parse(strings);
        int actual = Arrays.compare(numbers, resultArray);
        int expected = 0;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void parseTestWithNullParameter() {
        Integer[] expectedArray = new ArrayList<Integer>().toArray(Integer[]::new);
        Integer[] actualArray = parser.parse(null);
        int expected = 0;
        int actual = Arrays.compare(actualArray, expectedArray);
        Assert.assertEquals(actual, expected);
    }
}
