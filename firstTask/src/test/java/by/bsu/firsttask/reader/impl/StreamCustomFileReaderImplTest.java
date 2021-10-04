package by.bsu.firsttask.reader.impl;

import by.bsu.firsttask.exception.CustomReaderException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class StreamCustomFileReaderImplTest {
    private StreamCustomFileReaderImpl fileReader;

    @BeforeMethod
    public void initReader() {
        fileReader = new StreamCustomFileReaderImpl();
    }

    @Test(expectedExceptions = CustomReaderException.class)
    public void readLinesExceptionTest() throws CustomReaderException {
        List<String> lines = fileReader.readLines("jak.txt");
    }

    @Test(expectedExceptions = CustomReaderException.class)
    public void readLinesNullTest() throws CustomReaderException {
        List<String> lines = fileReader.readLines(null);
    }

    @Test
    public void readLinesTest() throws CustomReaderException {
        List<String> actual = fileReader.readLines("src/test/resources/data/data.txt");
        List<String> expected = new ArrayList<>(Arrays.asList("23, 45 324", "45.5 34 32"));
        Assert.assertEquals(actual, expected);
    }
}
