package by.bsu.informationhandling.parser;

import by.bsu.informationhandling.constant.ComponentRegex;
import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.entity.TextComposite;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphHandler implements CustomHandler {
    private final CustomHandler successor = new SentenceHandler();
    @Override
    public TextComposite handleRequest(String paragraphText) {
        TextComposite paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);
        String[] sentencesText = splitParagraphIntoSentences(paragraphText);
        for (String sentenceText: sentencesText) {
            TextComposite sentenceComponent = successor.handleRequest(sentenceText);
            paragraphComponent.add(sentenceComponent);
        }
        return paragraphComponent;
    }

    private String[] splitParagraphIntoSentences(String paragraphText) {
        ArrayList<String> sentences = new ArrayList<>();
        Pattern sentencePattern = Pattern.compile(ComponentRegex.SENTENCE.getRegex());
        Matcher matcher = sentencePattern.matcher(paragraphText);
        matcher.results()
                .forEach(result -> {
                    String currentSentence = paragraphText.substring(result.start(), result.end());
                    sentences.add(currentSentence);
                });
        return sentences.toArray(String[]::new);
    }
}
