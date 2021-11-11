package by.bsu.informationhandling.parser;

import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.entity.Symbol;
import by.bsu.informationhandling.entity.TextComposite;

public class LexemeHandler implements CustomHandler {
    @Override
    public TextComposite handleRequest(String lexemeText) {
        TextComposite lexemeComponent = new TextComposite(ComponentType.LEXEME);
        char[] symbols = lexemeText.toCharArray();
        for (char symbolText: symbols) {
            Symbol symbol = new Symbol(symbolText);
            lexemeComponent.add(symbol);
        }
        return lexemeComponent;
    }
}
