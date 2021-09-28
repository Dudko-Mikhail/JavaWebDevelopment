package by.epam.pretask.service;

import by.epam.pretask.entity.CustomNumber;
import by.epam.pretask.factory.CustomNumberFactory;

public class Adder {
    public static CustomNumber add(CustomNumber firstTerm, CustomNumber secondTerm) {
        double result = firstTerm.getNumber() + secondTerm.getNumber();
        return CustomNumberFactory.getInstance(result);
    }

    public static CustomNumber add(CustomNumber firstTerm, CustomNumber...terms) {
        double result = firstTerm.getNumber();
        for (CustomNumber term: terms) {
            result += term.getNumber();
        }
        return CustomNumberFactory.getInstance(result);
    }

    private Adder() {}
}
