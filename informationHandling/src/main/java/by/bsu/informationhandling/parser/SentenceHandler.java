package by.bsu.informationhandling.parser;

import by.bsu.informationhandling.constant.ComponentRegex;
import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.entity.TextComposite;

public class SentenceHandler implements CustomHandler {
    private final CustomHandler successor = new LexemeHandler();
    @Override
    public TextComposite handleRequest(String sentenceText) {
        TextComposite sentenceComponent = new TextComposite(ComponentType.SENTENCE);
        String[] lexemesText = sentenceText.split(ComponentRegex.LEXEME.getRegex());
        if (sentenceText.endsWith(" ")) {
            lexemesText[lexemesText.length - 1] += " ";
        }
        for (String lexemeText: lexemesText) {
            TextComposite lexemeComponent = successor.handleRequest(lexemeText);
            sentenceComponent.add(lexemeComponent);
        }
        return sentenceComponent;
    }
}
