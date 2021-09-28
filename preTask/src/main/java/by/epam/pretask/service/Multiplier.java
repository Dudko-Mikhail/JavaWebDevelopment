package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import by.epam.pretask.factory.CustomNumberFactory;

public class Multiplier {
    public static CustomNumber multiply(CustomNumber firstMultiplier, CustomNumber secondMultiplier) {
        double result = firstMultiplier.getNumber() * secondMultiplier.getNumber();
        return CustomNumberFactory.getInstance(result);
    }

    public static CustomNumber multiply(CustomNumber firstMultiplier, CustomNumber...multipliers) {
        double result = firstMultiplier.getNumber();
        for (CustomNumber multiplier: multipliers) {
            result *= multiplier.getNumber();
        }
        return CustomNumberFactory.getInstance(result);
    }

    private Multiplier() {}
}
