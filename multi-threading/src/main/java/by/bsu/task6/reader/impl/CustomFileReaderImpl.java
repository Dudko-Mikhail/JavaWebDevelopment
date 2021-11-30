package by.bsu.task6.reader.impl;

import by.bsu.task6.exception.ReaderException;
import by.bsu.task6.reader.CustomFileReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CustomFileReaderImpl implements CustomFileReader {
    @Override
    public List<String> readLines(String fileName) throws ReaderException {
        if (fileName == null) {
            throw new ReaderException("file name is null");
        }
        Path path = Path.of(fileName);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

}
