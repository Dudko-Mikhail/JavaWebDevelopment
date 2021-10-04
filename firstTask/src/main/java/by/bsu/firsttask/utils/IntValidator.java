package by.bsu.firsttask.utils;

public class IntValidator {
    public static final String INT_REGEX = "[-+]?\\d+";

    public static boolean isValid(String number) {
        if (number == null) {
            return false;
        }
        return number.matches(INT_REGEX);
    }

    private IntValidator() {}
}
