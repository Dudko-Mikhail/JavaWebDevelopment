package by.bsu.informationhandling.service.impl;

import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.constant.SymbolType;
import by.bsu.informationhandling.entity.TextComposite;
import by.bsu.informationhandling.exception.ServiceException;
import by.bsu.informationhandling.parser.impl.SentenceHandler;
import by.bsu.informationhandling.parser.impl.TextHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextHandlingServiceImplTest {
    private TextHandlingServiceImpl service;

    @BeforeMethod
    public void initService() {
        service = TextHandlingServiceImpl.getInstance();
    }

    @DataProvider(name = "findSymbolAmountInSentenceProvider")
    public Object[][] sentenceCriteriaAnswerProvider() {
        return new Object[][] {
                {"Hello world!", EnumSet.of(SymbolType.CONSONANT_ENGLISH, SymbolType.VOWEL_ENGLISH), Map.of(SymbolType.CONSONANT_ENGLISH, 7, SymbolType.VOWEL_ENGLISH, 3)},
                {"Hello world!", EnumSet.of(SymbolType.CONSONANT_RUSSIAN, SymbolType.VOWEL_RUSSIAN), Map.of(SymbolType.CONSONANT_RUSSIAN, 0, SymbolType.VOWEL_RUSSIAN, 0)},
                {"Привет мир!", EnumSet.of(SymbolType.CONSONANT_RUSSIAN, SymbolType.VOWEL_RUSSIAN), Map.of(SymbolType.CONSONANT_RUSSIAN, 6, SymbolType.VOWEL_RUSSIAN, 3)},
                {"Доброго дня\nвсем вам.", EnumSet.of(SymbolType.SPACE, SymbolType.NEW_LINE), Map.of(SymbolType.SPACE, 2, SymbolType.NEW_LINE, 1)},
                {"12345 я иду искать в подъезд.", EnumSet.of(SymbolType.DIGIT, SymbolType.SIGN_RUSSIAN), Map.of(SymbolType.DIGIT, 5, SymbolType.SIGN_RUSSIAN, 2)},
                {"12345 я иду искать в подъезд.", null, new HashMap<>()}
        };
    }

    @DataProvider(name = "deleteSentencesFromTextWithWordsLessThanGivenProvider")
    public Object[][] textBorderAnswerProvider() {
        return new Object[][] {
                {"    Hello world!. Go. Go! Go.\n    It's not the end. Good!", 2, "    Hello world!.\n    It's not the end.\n"},
                {"    Hello world!. Go. Go! Go.\n    It's not the end. Good!", 4, "    It's not the end.\n"},
                {"    Hello world!. Go. Go! Go.\n    It's not the end. Good!", 5, ""},
        };
    }

    @DataProvider(name = "findSentenceWithLongestWordProvider")
    public Object[][] textAndAnswerProvider() {
        return new Object[][] {
                {"    My name is Misha. Это точь-в-точь так.", "Это точь-в-точь так."},
                {"    Hello word! The-longest-word good.\n    Good bye!", "The-longest-word good."},
                {"    Hello word! Is the-longest-\nword good or bad?\n    It's not the end.", "Is the-longest-\nword good or bad?"}
        };
    }

    @DataProvider(name = "findSameWordsAndTheirCountInTextProvider")
    public Object[][] textAndAnswerMapProvider() {
        return new Object[][] {
                {"    Hello heLlO worD! Word is good. Is it right! No, it isn't", Map.of("hello", 2, "is", 2, "word", 2)},
                {"    Help me please. Please, help me. I need help. So do i.", Map.of("help", 3, "me", 2, "please", 2, "i", 2)},
                {"    Точь-в-точь так всё и было, точь-в-точь. It's my life! It's a big cat.", Map.of("точь-в-точь", 2, "it's", 2)},
                {"    Точь-в-точь так всё и было, точь-в-точь. It's my life! It's a big cat.", Map.of("точь-в-точь", 2, "it's", 2)},
                {"    Привет сине-\nбелый снегопад. Был он сине-\nбелый.", Map.of("сине-белый", 2)}, // Дефис и перенос в конце строки неразличимы
                {"    Жили у бабуси два весё-\nлых гуся...\nДва весё-\nлых гуся.", Map.of("гуся", 2, "весё-лых", 2, "два", 2)}
        };
    }

    @Test
    public void sortParagraphsAccordingSentenceCountTest() throws ServiceException {
        String text = """
                    Hello world! Good day. Is you fine? Everything is ok.
                    Second paragraph. Second sentence.
                    Third paragraph...
                """;
        TextHandler handler = new TextHandler();
        TextComposite composite = handler.handleRequest(text);
        List<TextComposite> paragraphs = service.sortParagraphsAccordingSentenceCount(composite);
        String[] expected = {"Third paragraph...", "Second paragraph. Second sentence.",
                            "Hello world! Good day. Is you fine? Everything is ok."};
        String[] actual = paragraphs.stream()
                                    .map(c -> c.restoreText().stripTrailing())
                                    .toArray(String[]::new);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "findSentenceWithLongestWordProvider")
    public void findSentenceWithLongestWordTest(String text, String expected) throws ServiceException {
        TextHandler handler = new TextHandler();
        TextComposite composite = handler.handleRequest(text);
        TextComposite sentence = service.findSentenceWithLongestWord(composite);
        String actual = sentence.restoreText().stripTrailing();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "deleteSentencesFromTextWithWordsLessThanGivenProvider")
    public void deleteSentencesFromTextWithWordsLessThanGivenTest(String text, int border, String expected) throws ServiceException {
        TextHandler handler = new TextHandler();
        TextComposite composite = handler.handleRequest(text);
        service.deleteSentencesFromTextWithWordsLessThanGiven(composite, border);
        String actual = composite.restoreText();
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "findSameWordsAndTheirCountInTextProvider")
    public void findSameWordsAndTheirCountInTextTest(String text, Map<String, Integer> expected) throws ServiceException {
        TextHandler handler = new TextHandler();
        TextComposite composite = handler.handleRequest(text);
        Map<String, Integer> actual = service.findSameWordsAndTheirCountInText(composite);
        Assert.assertEquals(actual, expected);
    }

    @Test(dataProvider = "findSymbolAmountInSentenceProvider")
    public void findSymbolAmountInSentenceTest(String sentence, EnumSet<SymbolType> criteria, Map<SymbolType, Integer> expected) throws ServiceException {
        SentenceHandler handler = new SentenceHandler();
        TextComposite sentenceComponent = handler.handleRequest(sentence);
        Map<SymbolType, Integer> actual = service.findSymbolAmountInSentence(sentenceComponent, criteria);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findSentenceWithLongestWordExceptionTest() throws ServiceException {
        TextComposite paragraph = new TextComposite(ComponentType.PARAGRAPH);
        service.findSentenceWithLongestWord(paragraph);
    }
}