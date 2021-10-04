package by.bsu.firsttask.service.impl;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;
import by.bsu.firsttask.service.CalculationService;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class IntStreamCalculationServiceImpl implements CalculationService {
    @Override
    public OptionalInt findMin(CustomArray array) {
        if (array == null) {
            return OptionalInt.empty();
        }
        IntStream stream = IntStream.of(array.getArray());
        return stream.min();
    }

    @Override
    public OptionalInt findMax(CustomArray array) {
        if (array == null) {
            return OptionalInt.empty();
        }
        IntStream stream = IntStream.of(array.getArray());
        return stream.max();
    }

    @Override
    public OptionalDouble findAverage(CustomArray array) {
        if (array == null) {
            return OptionalDouble.empty();
        }
        IntStream stream = IntStream.of(array.getArray());
        return stream.average();
    }

    @Override
    public int findSum(CustomArray array) {
        int sum = 0;
        if (array != null) {
            IntStream stream = IntStream.of(array.getArray());
            sum = stream.sum();
        }
        return sum;
    }

    @Override
    public int countPositiveNumbers(CustomArray array) {
        int count = 0;
        if (array != null) {
            IntStream stream = IntStream.of(array.getArray());
            count = (int) stream.filter(a -> a > 0).count();
        }
        return count;
    }

    @Override
    public int countNegativeNumbers(CustomArray array) {
        int count = 0;
        if (array != null) {
            IntStream stream = IntStream.of(array.getArray());
            count = (int) stream.filter(a -> a < 0).count();
        }
        return count;
    }

    @Override
    public void multiplyByNumber(CustomArray array, int multiplier) throws CustomArrayException {
        if (array == null) {
            throw new CustomArrayException("Cannot multiply array elements: array is null");
        }
        int[] result = Arrays.stream(array.getArray())
                .map(a -> a * multiplier)
                .toArray();
        array.setArray(result);
    }
}
