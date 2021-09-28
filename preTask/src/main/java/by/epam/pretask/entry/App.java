package by.epam.pretask.entry;

import by.epam.pretask.entity.CustomNumber;
import by.epam.pretask.exceptions.CustomNumberParseException;
import by.epam.pretask.factory.CustomNumberFactory;
import by.epam.pretask.reader.CustomNumbersFileReader;
import by.epam.pretask.service.Adder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;


public class App {
    private static Logger logger = LogManager.getLogger();

    public static void main( String[] args ) {
        ArrayList<CustomNumber> customNumbers;
        CustomNumbersFileReader reader = new CustomNumbersFileReader();
        try {
            customNumbers = reader.readNumbers("data/numbers.txt");
            System.out.println(customNumbers);
        } catch (IOException | CustomNumberParseException e) {
            logger.catching(e);
        }
    }
}
