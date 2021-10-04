package by.bsu.firsttask.creator;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;

import java.util.Arrays;
import java.util.Collection;

public class CustomArrayCreator {

    public CustomArrayCreator() {}
    public CustomArray createCustomArray(Collection<Integer> collection) throws CustomArrayException {
        if (collection == null) {
            throw new CustomArrayException("Cannot create CustomArray: collection is null");
        }
        Integer[] array = collection.toArray(Integer[]::new);
        return createCustomArray(array);
    }

    public CustomArray createCustomArray(int...numbers) throws CustomArrayException {
        if (numbers == null) {
            throw new CustomArrayException("Cannot create CustomArray: numbers is null");
        }
        return new CustomArray(numbers);
    }

    public CustomArray createCustomArray(Integer[] numbers) throws CustomArrayException {
        if (numbers == null) {
            throw new CustomArrayException("Cannot create CustomArray: numbers is null");
        }
        int[] array = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            array[i] = numbers[i];
        }
        return new CustomArray(array);
    }

    public CustomArray createCustomArray(int length) throws CustomArrayException {
        if (length < 0) {
            throw new CustomArrayException("Cannot create CustomArray: length < 0");
        }
        return new CustomArray(length);
    }

    public CustomArray createFilledCustomArray(int length, int value) throws CustomArrayException {
        if (length < 0) {
            throw new CustomArrayException("Cannot create CustomArray: length < 0");
        }
        int[] array = new int[length];
        Arrays.fill(array, value);
        return new CustomArray(array);
    }
}
