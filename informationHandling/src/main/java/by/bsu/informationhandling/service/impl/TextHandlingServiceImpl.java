package by.bsu.informationhandling.service.impl;

import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.constant.SymbolType;
import by.bsu.informationhandling.entity.AbstractComponent;
import by.bsu.informationhandling.entity.Symbol;
import by.bsu.informationhandling.entity.TextComposite;
import by.bsu.informationhandling.exception.ServiceException;
import by.bsu.informationhandling.service.TextHandlingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextHandlingServiceImpl implements TextHandlingService {
    private static final Logger logger = LogManager.getLogger();
    private static final String WORD_REGEX = "(?ui)[a-zа-яё]+?((['-]|-\n)?[a-zа-яё]+)*"; // Дефис и перенос слова в конце строки неразличимы между собой
    private static TextHandlingServiceImpl instance;

    public static TextHandlingServiceImpl getInstance() {
        if (instance == null) {
            instance = new TextHandlingServiceImpl();
        }
        return instance;
    }

    @Override
    public List<TextComposite> sortParagraphsAccordingSentenceCount(TextComposite text) throws ServiceException {
        checkComponentType(text, ComponentType.TEXT);
        int size = text.getComponentsAmount();
        List<TextComposite> paragraphs = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            paragraphs.add((TextComposite) text.getChild(i));
        }
        paragraphs.sort(Comparator.comparingInt(TextComposite::getComponentsAmount));
        return paragraphs;
    }

    @Override
    public TextComposite findSentenceWithLongestWord(TextComposite text) throws ServiceException {
        checkComponentType(text, ComponentType.TEXT);
        TextComposite sentenceWithLongestWord = null;
        int maxLength = 0;
        for (int i = 0; i < text.getComponentsAmount(); i++) {
            TextComposite currentParagraph = (TextComposite) text.getChild(i);
            for (int j = 0; j < currentParagraph.getComponentsAmount(); j++) {
                TextComposite currentSentence = (TextComposite) currentParagraph.getChild(j);
                String sentenceText = currentSentence.restoreText();
                String[] words = findWords(sentenceText);
                int currentMaxLength = Arrays.stream(words)
                                             .mapToInt(String::length)
                                             .max()
                                             .orElse(0);
                if (currentMaxLength > maxLength) {
                    maxLength = currentMaxLength;
                    sentenceWithLongestWord = currentSentence;
                }
            }
        }
        return sentenceWithLongestWord;
    }

    @Override
    public void deleteSentencesFromTextWithWordsLessThanGiven(TextComposite text, int border) throws ServiceException {
        checkComponentType(text, ComponentType.TEXT);
        Iterator<AbstractComponent> paragraphIterator = text.getChildren().iterator();
        while (paragraphIterator.hasNext()) {
            TextComposite paragraph = (TextComposite) paragraphIterator.next();
            Iterator<AbstractComponent> sentenceIterator = paragraph.getChildren().iterator();
            while (sentenceIterator.hasNext()) {
                String sentence = sentenceIterator.next().restoreText();
                int wordsCount = findWordsCount(sentence);
                if (wordsCount < border) {
                    sentenceIterator.remove();
                }
            }
            if (paragraph.getComponentsAmount() == 0) {
                paragraphIterator.remove();
            }
        }
    }

    @Override
    public Map<String, Integer> findSameWordsAndTheirCountInText(TextComposite text) throws ServiceException {
        checkComponentType(text, ComponentType.TEXT);
        Map<String, Integer> wordsAndCount = new HashMap<>();
        String[] words = findWords(text.restoreText());
        for (String word: words) {
            String wordInLowerCase = word.toLowerCase();
            int currentCount = wordsAndCount.getOrDefault(wordInLowerCase, 0);
            wordsAndCount.put(wordInLowerCase, currentCount + 1);
        }
        return wordsAndCount.entrySet()
                            .stream()
                            .filter(e -> e.getValue() > 1)
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public Map<SymbolType, Integer> findSymbolAmountInSentence(TextComposite sentence, EnumSet<SymbolType> searchCriteria) throws ServiceException {
        checkComponentType(sentence, ComponentType.SENTENCE);
        if (searchCriteria == null) {
            return new EnumMap<>(SymbolType.class);
        }
        Map<SymbolType, Integer> result = new EnumMap<>(SymbolType.class);
        for (SymbolType criterion: searchCriteria) {
            result.put(criterion, 0);
        }
        char[] sentenceSymbols = sentence.restoreText()
                                         .stripTrailing()
                                         .toCharArray();
        for (char sentenceSymbol : sentenceSymbols) {
            SymbolType symbolType = Symbol.defineSymbolType(sentenceSymbol);
            for (SymbolType criterion : searchCriteria) {
                if (symbolType == criterion) {
                    int currentCount = result.get(symbolType);
                    result.put(symbolType, currentCount + 1);
                }
            }
        }
        return result;
    }

    private void checkComponentType(TextComposite composite, ComponentType expectedType) throws ServiceException {
        if (composite == null || composite.getComponentType() != expectedType) {
            ComponentType actualType = composite != null ? composite.getComponentType() : null;
            ServiceException exception = new ServiceException("Invalid component type " + actualType + ". Expected type is " + expectedType);
            logger.catching(exception);
            throw exception;
        }
    }

    private String[] findWords(String text) {
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(text);
        ArrayList<String> words = new ArrayList<>();
        matcher.results().forEach(r -> words.add(text.substring(r.start(), r.end())
                                                     .replace("\n", "")));
        return words.toArray(String[]::new);
    }

    private int findWordsCount(String text) {
        Pattern pattern = Pattern.compile(WORD_REGEX);
        Matcher matcher = pattern.matcher(text);
        return (int) matcher.results().count();
    }

    private TextHandlingServiceImpl() {}
}