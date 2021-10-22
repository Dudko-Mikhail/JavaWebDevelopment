package by.bsu.shapetask.util;

import by.bsu.shapetask.exception.CustomReaderException;
import by.bsu.shapetask.validator.LineValidator;

import java.util.Arrays;

public class CustomParser {
    private static CustomParser customParser;
    private static final String DELIMITER_REGEX = "\\s+";

    public static CustomParser getInstance() {
        if (customParser == null) {
            customParser = new CustomParser();
        }
        return customParser;
    }

    public double[] parseLine(String line) throws CustomReaderException {
        LineValidator validator = LineValidator.getInstance();
        if (!validator.validateLine(line)) {
            throw new CustomReaderException("Cannot parse line: line isn't valid");
        }
        return Arrays.stream(line.split(DELIMITER_REGEX))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    private CustomParser() {
    }
}
