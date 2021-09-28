package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import by.epam.pretask.factory.CustomNumberFactory;

public class Divider {
    public static CustomNumber divide(CustomNumber dividend, CustomNumber divider) {
        double result = dividend.getNumber() / divider.getNumber();
        return CustomNumberFactory.getInstance(result);
    }

    public static CustomNumber divide(CustomNumber first, CustomNumber...dividers) {
        double result = first.getNumber();
        for (CustomNumber divider: dividers) {
            result /= divider.getNumber();
        }
        return CustomNumberFactory.getInstance(result);
    }

    private Divider() {}
}
