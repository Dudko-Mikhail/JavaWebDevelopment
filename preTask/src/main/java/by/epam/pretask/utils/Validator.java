package by.epam.pretask.utils;

import by.epam.pretask.exceptions.CustomNumberParseException;

public class Validator {
    public static void validate(String number) throws CustomNumberParseException {
        try {
            Double.parseDouble(number);
        }
        catch (NumberFormatException e) {
            throw new CustomNumberParseException("Invalid number format: " + number, e);
        }
    }

    private Validator() {}
}
