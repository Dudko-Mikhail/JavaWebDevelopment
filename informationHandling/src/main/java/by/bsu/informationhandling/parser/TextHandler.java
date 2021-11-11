package by.bsu.informationhandling.parser;

import by.bsu.informationhandling.constant.ComponentRegex;
import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.entity.TextComposite;

public class TextHandler implements CustomHandler {
    private final CustomHandler successor = new ParagraphHandler();
    @Override
    public TextComposite handleRequest(String text) {
        TextComposite textComponent = new TextComposite(ComponentType.TEXT);
        String[] paragraphsText = text.split(ComponentRegex.PARAGRAPH.getRegex()); // 0 параграф - пустая строка
        for (int i = 1, paragraphsTextLength = paragraphsText.length; i < paragraphsTextLength; i++) {
            TextComposite paragraphComponent = successor.handleRequest(paragraphsText[i]);
            textComponent.add(paragraphComponent);
        }
        return textComponent;
    }
}
