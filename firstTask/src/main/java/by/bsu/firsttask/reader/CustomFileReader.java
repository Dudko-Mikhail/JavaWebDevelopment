package by.bsu.firsttask.reader;

import by.bsu.firsttask.exception.CustomReaderException;

import java.util.List;

public interface CustomFileReader {

    List<String> readLines(String fileName) throws CustomReaderException;
}
