package by.bsu.firsttask.service.impl;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

public class SortingServiceImplTest {
    private SortingServiceImpl sortingService;

    @BeforeMethod
    public void initSortingService() {
        sortingService = new SortingServiceImpl();
    }

    @DataProvider(name = "customArrayProvider")
    public Object[][] provider() {
        return new Object[][] {
                {new CustomArray(4, 4, 4, 4)},
                {new CustomArray(-9, 15, 82, -7, -8, -3, 28)},
                {new CustomArray(6, 9, 2, 1, -9, 48, 71, -3)}
        };
    }

    @Test(dataProvider = "customArrayProvider")
    public void bubbleSortTest(CustomArray source) throws CustomArrayException {
        int[] expected = source.getArray();
        Arrays.sort(expected);
        sortingService.bubbleSort(source);
        int[] actual = source.getArray();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "customArrayProvider")
    public void insertionSortTest(CustomArray source) throws CustomArrayException {
        int[] expected = source.getArray();
        Arrays.sort(expected);
        sortingService.insertionSort(source);
        int[] actual = source.getArray();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "customArrayProvider")
    public void selectionSortTest(CustomArray source) throws CustomArrayException {
        int[] expected = source.getArray();
        Arrays.sort(expected);
        sortingService.selectionSort(source);
        int[] actual = source.getArray();
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = CustomArrayException.class)
    public void selectionSortExceptionTest() throws CustomArrayException {
        sortingService.selectionSort(null);
    }
}
