package by.bsu.shapetask.reader.impl;

import by.bsu.shapetask.exception.CustomReaderException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CustomFileReaderImplTest {
    private CustomFileReaderImpl fileReader;


    @BeforeMethod
    public void initReader() {
        fileReader = new CustomFileReaderImpl();
    }

    @Test(expectedExceptions = CustomReaderException.class)
    public void readLinesExceptionTest() throws CustomReaderException {
        List<String> lines = fileReader.readLines("7");
    }

    @Test(expectedExceptions = CustomReaderException.class)
    public void readLinesNullTest() throws CustomReaderException {
        List<String> lines = fileReader.readLines(null);
    }

    @Test
    public void readLinesTest() throws CustomReaderException {
        List<String> actual = fileReader.readLines("src/test/resources/data/data.txt");
        List<String> expected = new ArrayList<>(Arrays.asList("3.9 8 6 2 7 4 1 9.5", "9 6 8 14 3 95 32 8"));
        Assert.assertEquals(actual, expected);
    }
}
