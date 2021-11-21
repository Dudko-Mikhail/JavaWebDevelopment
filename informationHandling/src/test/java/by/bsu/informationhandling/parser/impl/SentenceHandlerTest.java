package by.bsu.informationhandling.parser.impl;

import by.bsu.informationhandling.entity.TextComposite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SentenceHandlerTest {
    public SentenceHandler handler;

    @BeforeMethod
    public void initHandler() {
        handler = new SentenceHandler();
    }

    @DataProvider(name = "sentenceProvider")
    public Object[][] provider1() {
        return new Object[][] {
                {"Help me\nplease! "},
                {"What is your name?\n"},
                {"It was a long day..."},
                {"Здравствуйте, Миха-\nил."},
        };
    }

    @Test(dataProvider = "sentenceProvider")
    public void StringHandleRequestTest(String text) {
        TextComposite composite = handler.handleRequest(text);
        String actual = composite.restoreText().stripTrailing();
        String expected = text.stripTrailing();
        Assert.assertEquals(actual, expected);
    }
}
