package by.epam.pretask.reader;

import by.epam.pretask.entity.CustomNumber;
import by.epam.pretask.exceptions.CustomNumberParseException;
import by.epam.pretask.factory.CustomNumberFactory;
import by.epam.pretask.utils.Validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class CustomNumbersFileReader {
    private static final String SPACE_SPLITERATOR = "\s+";

    public ArrayList<CustomNumber> readNumbers(String filePath) throws IOException, CustomNumberParseException {
        ArrayList<CustomNumber> customNumbers = new ArrayList<>();
        Path path = Path.of(filePath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while (reader.ready()) {
                line = reader.readLine().strip();
                String[] stringNumbers = line.split(SPACE_SPLITERATOR);
                for (int i = 0; i < stringNumbers.length; i++) {
                    Validator.validate(stringNumbers[i]);
                    double value = Double.parseDouble(stringNumbers[i]);
                    CustomNumber customNumber = CustomNumberFactory.getInstance(value);
                    customNumbers.add(customNumber);
                }
            }
        }
        return customNumbers;
    }
}
