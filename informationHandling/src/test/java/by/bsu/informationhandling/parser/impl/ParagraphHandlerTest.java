package by.bsu.informationhandling.parser.impl;

import by.bsu.informationhandling.entity.TextComposite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParagraphHandlerTest {
    public ParagraphHandler handler;

    @BeforeMethod
    public void initHandler() {
        handler = new ParagraphHandler();
    }

    @DataProvider(name = "paragraphProvider")
    public Object[][] provider1() {
        return new Object[][] {
                {"Hello world. My day is good.\n What's your name? My name id Mis-\nha."},
                {"Привет, мир! Точь-в-точь так всё и было. Всё было сделано не зря."}
        };
    }

    @Test(dataProvider = "paragraphProvider")
    public void StringHandleRequestTest(String text) {
        TextComposite composite = handler.handleRequest(text);
        String actual = composite.restoreText().stripTrailing();
        String expected = text.stripTrailing();
        Assert.assertEquals(actual, expected);
    }
}
