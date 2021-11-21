package by.bsu.informationhandling.parser.impl;

import by.bsu.informationhandling.entity.TextComposite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LexemeHandlerTest {
    public LexemeHandler handler;

    @BeforeMethod
    public void initHandler() {
        handler = new LexemeHandler();
    }

    @DataProvider(name = "lexemeProvider")
    public Object[][] provider1() {
        return new Object[][] {
                {"Hello."},
                {"good\njob..."},
                {"what?!"},
                {"Ми-\nша"},
        };
    }

    @Test(dataProvider = "lexemeProvider")
    public void StringHandleRequestTest(String text) {
        TextComposite composite = handler.handleRequest(text);
        String actual = composite.restoreText().stripTrailing();
        String expected = text.stripTrailing();
        Assert.assertEquals(actual, expected);
    }
}
