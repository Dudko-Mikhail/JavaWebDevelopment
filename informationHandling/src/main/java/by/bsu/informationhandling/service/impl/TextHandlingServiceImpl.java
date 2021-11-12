package by.bsu.informationhandling.service.impl;

import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.constant.SymbolType;
import by.bsu.informationhandling.entity.Symbol;
import by.bsu.informationhandling.entity.TextComposite;
import by.bsu.informationhandling.exception.ServiceException;
import by.bsu.informationhandling.service.TextHandlingService;

import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextHandlingServiceImpl implements TextHandlingService {
    private static final String WORD_REGEX = "(?ui)[a-zа-я]+?(('|-|-\n)?[a-zа-я]+)*";
    private static TextHandlingServiceImpl INSTANCE;

    public static TextHandlingServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TextHandlingServiceImpl();
        }
        return INSTANCE;
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
        for (int i = 0; i < text.getComponentsAmount(); i++) {
            TextComposite paragraph = (TextComposite) text.getChild(i);
            for (int j = 0; j < paragraph.getComponentsAmount(); j++) {
                String sentence = paragraph.getChild(j).restoreText();
                int wordsCount = findWordsCount(sentence);
                if (wordsCount < border) {
                    paragraph.remove(paragraph.getChild(j));
                    j--;
                }
            }
            if (paragraph.getComponentsAmount() == 0) {
                text.remove(paragraph);
                i--;
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
            return new HashMap<>();
        }
        Map<SymbolType, Integer> result = new HashMap<>();
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

    private void checkComponentType(TextComposite composite, ComponentType type) throws ServiceException {
        if (composite == null || composite.getComponentType() != type) {
            throw new ServiceException();
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