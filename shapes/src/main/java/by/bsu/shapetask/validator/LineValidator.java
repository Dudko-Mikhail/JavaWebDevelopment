package by.bsu.shapetask.validator;

import java.util.Arrays;

public class LineValidator {
    private final String DOUBLE_REGEX = "[+-]?(0|([1-9]\\d*))(\\.\\d+)?";
    private final String DELIMITER_REGEX = "\\s+";
    public final int REQUIRED_DOUBLE_AMOUNT = 6;
    private static LineValidator instance;

    public static LineValidator getInstance() {
        if (instance == null) {
            instance = new LineValidator();
        }
        return  instance;
    }

    public boolean validateLine(String text) {
        if (text == null) {
            return false;
        }
        String[] numbers = text.split(DELIMITER_REGEX);
        return numbers.length == REQUIRED_DOUBLE_AMOUNT && Arrays.stream(numbers)
                                                                 .allMatch(this::isDouble);
    }

    private boolean isDouble(String text) {
        if (text == null) {
            return false;
        }
        return text.matches(DOUBLE_REGEX);
    }

    private LineValidator() {}
}
