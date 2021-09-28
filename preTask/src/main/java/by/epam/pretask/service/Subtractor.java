package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import by.epam.pretask.factory.CustomNumberFactory;

public class Subtractor {
    public static CustomNumber subtract(CustomNumber minuend, CustomNumber subtrahend) {
        double result = minuend.getNumber() - subtrahend.getNumber();
        return CustomNumberFactory.getInstance(result);
    }

    public static CustomNumber subtract(CustomNumber minuend, CustomNumber...deductibleNumbers) {
        double result = minuend.getNumber();
        for (CustomNumber number: deductibleNumbers) {
            result -= number.getNumber();
        }
        return CustomNumberFactory.getInstance(result);
    }

    private Subtractor() {}
}
