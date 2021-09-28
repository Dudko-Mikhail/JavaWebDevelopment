package by.epam.pretask.factory;

import by.epam.pretask.entity.CustomNumber;

public class CustomNumberFactory {
    public static CustomNumber getInstance(double number) {
        return new CustomNumber(number);
    }

    private CustomNumberFactory() {}
}
