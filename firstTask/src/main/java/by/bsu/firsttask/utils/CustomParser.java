package by.bsu.firsttask.utils;

import java.util.ArrayList;
import java.util.List;

public class CustomParser {
    public static final String DELIMITER_REGEX = "\\s+";

    public Integer[] parse(List<String> lines) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (lines != null) {
            for (String line : lines) {
                if (line != null) {
                    integers.addAll(parseLine(line));
                }
            }
        }
        return integers.toArray(Integer[]::new);
    }

    private List<Integer> parseLine(String line) {
        ArrayList<Integer> integers = new ArrayList<>();
        String[] numbers = line.strip()
                               .split(DELIMITER_REGEX);
        for (String s : numbers) {
            if (IntValidator.isValid(s)) {
                int number = Integer.parseInt(s);
                integers.add(number);
            }
        }
        return integers;
    }
}
