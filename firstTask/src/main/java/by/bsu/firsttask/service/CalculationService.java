package by.bsu.firsttask.service;

import by.bsu.firsttask.entity.CustomArray;
import by.bsu.firsttask.exception.CustomArrayException;

import java.util.OptionalDouble;
import java.util.OptionalInt;

public interface CalculationService {
    OptionalInt findMin(CustomArray array);
    OptionalInt findMax(CustomArray array);
    OptionalDouble findAverage(CustomArray array);
    int findSum(CustomArray array);
    int countPositiveNumbers(CustomArray array);
    int countNegativeNumbers(CustomArray array);
    void multiplyByNumber(CustomArray array, int multiplier) throws CustomArrayException;
}
