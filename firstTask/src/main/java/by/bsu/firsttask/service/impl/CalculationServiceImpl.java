package by.bsu.firsttask.service.impl;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;
import by.bsu.firsttask.service.CalculationService;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public class CalculationServiceImpl implements CalculationService {
    @Override
    public OptionalInt findMin(CustomArray array) {
        if (array == null || array.length() == 0) {
            return OptionalInt.empty();
        }
        int min = array.getElement(0);
        for (int i = 0; i < array.length(); i++) {
            int element = array.getElement(i);
            if (element < min) {
                min = element;
            }
        }
        return OptionalInt.of(min);
    }

    @Override
    public OptionalInt findMax(CustomArray array) {
        if (array == null || array.length() == 0) {
            return OptionalInt.empty();
        }
        int max = array.getElement(0);
        for (int i = 0; i < array.length(); i++) {
            int element = array.getElement(i);
            if (element > max) {
                max = element;
            }
        }
        return OptionalInt.of(max);
    }

    @Override
    public OptionalDouble findAverage(CustomArray array) {
        if (array == null || array.length() == 0) {
            return OptionalDouble.empty();
        }
        double average = 0;
        for (int i = 0; i < array.length(); i++) {
            average += array.getElement(i);
        }
        average /= array.length();
        return OptionalDouble.of(average);
    }

    @Override
    public int findSum(CustomArray array) {
        int sum = 0;
        if (array != null && array.length() != 0) {
            for (int i = 0; i < array.length(); i++) {
                sum += array.getElement(i);
            }
        }
        return sum;
    }

    @Override
    public int countPositiveNumbers(CustomArray array) {
        int count = 0;
        if (array != null && array.length() != 0) {
            for (int i = 0; i < array.length(); i++) {
                if (array.getElement(i) > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public int countNegativeNumbers(CustomArray array) {
        int count = 0;
        if (array != null && array.length() != 0) {
            for (int i = 0; i < array.length(); i++) {
                if (array.getElement(i) < 0) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    public void multiplyByNumber(CustomArray array, int multiplier) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("Cannot multiply array elements: array is null");
        }
        int[] result = array.getArray();
        for (int i = 0; i < result.length; i++) {
            result[i] *= multiplier;
        }
        array.setArray(result);
    }
}
