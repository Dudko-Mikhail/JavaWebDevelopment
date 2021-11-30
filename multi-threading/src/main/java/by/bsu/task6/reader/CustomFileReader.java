package by.bsu.task6.reader;

import by.bsu.task6.exception.ReaderException;

import java.util.List;

public interface CustomFileReader {
    List<String> readLines(String fileName) throws ReaderException;
}
