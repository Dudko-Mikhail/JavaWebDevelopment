package by.bsu.firsttask.creator;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CustomArrayCreatorTest {
    private CustomArrayCreator creator;

    @DataProvider(name = "length_value")
    public Object[][] lengthValueProvider() {
        return new Object[][] {
                {5, 8},
                {7, 9},
                {0, 0},
                {9, -45}
        };
    }

    @BeforeMethod
    public void initCreator() {
        creator = new CustomArrayCreator();
    }

    @Test(expectedExceptions = {CustomArrayException.class})
    public void createCustomArrayWithNullIntArrayTest() throws CustomArrayException {
        int[] array = null;
        creator.createCustomArray(array);
    }

    @Test(expectedExceptions = {CustomArrayException.class})
    public void createCustomArrayWithNullIntegerArrayTest() throws CustomArrayException {
        Integer[] array = null;
        creator.createCustomArray(array);
    }

    @Test
    public void createCustomArrayWithIntegerArrayTest() throws CustomArrayException {
        Integer[] array = {5, 9, 1, 2};
        CustomArray expected = new CustomArray(5, 9, 1, 2);
        CustomArray actual = creator.createCustomArray(array);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void createCustomArrayWithIntNumbersTest() throws CustomArrayException {
        CustomArray expected = new CustomArray(7, 3, 1);
        CustomArray actual = creator.createCustomArray(7, 3, 1);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void createCustomArrayWithCollectionTest() throws CustomArrayException {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(4, 9, 7, 12));
        CustomArray expected = new CustomArray(4, 9, 7, 12);
        CustomArray actual = creator.createCustomArray(list);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "length_value")
    private void createFilledCustomArrayTest(int length, int value) throws CustomArrayException {
        int[] array = new int[length];
        Arrays.fill(array, value);
        CustomArray expected = new CustomArray(array);
        CustomArray actual = creator.createFilledCustomArray(length, value);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = {CustomArrayException.class})
    private void createFilledCustomArrayWithExceptionTest() throws CustomArrayException {
        creator.createFilledCustomArray(-9, 45);
    }


}
