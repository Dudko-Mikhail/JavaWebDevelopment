package by.bsu.informationhandling.parser;

import by.bsu.informationhandling.entity.TextComposite;

public interface CustomHandler {
    TextComposite handleRequest(String text);
}
