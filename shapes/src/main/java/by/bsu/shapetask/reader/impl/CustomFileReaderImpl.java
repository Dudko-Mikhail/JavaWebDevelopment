package by.bsu.shapetask.reader.impl;

import by.bsu.shapetask.exception.CustomReaderException;
import by.bsu.shapetask.reader.CustomFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CustomFileReaderImpl implements CustomFileReader {
    private static Logger logger = LogManager.getLogger();

    public List<String> readLines(String filePath) throws CustomReaderException {
        if (filePath == null) {
            throw new CustomReaderException("Cannot read from file: filePath is null");
        }
        Path path = Path.of(filePath);
        if (!Files.exists(path)) {
            throw new CustomReaderException("File " + path.normalize() + " doesn't exist");
        }
        List<String> lines = new ArrayList<>();
        try {
            lines.addAll(Files.readAllLines(path));
        } catch (IOException e) {
            logger.catching(e);
        }
        return lines;
    }
}
