package by.bsu.shapetask.reader;

import by.bsu.shapetask.exception.CustomReaderException;

import java.util.List;

public interface CustomFileReader {
    List<String> readLines(String fileName) throws CustomReaderException;
}
