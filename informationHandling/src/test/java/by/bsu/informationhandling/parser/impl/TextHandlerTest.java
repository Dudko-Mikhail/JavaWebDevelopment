package by.bsu.informationhandling.parser.impl;

import by.bsu.informationhandling.entity.TextComposite;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TextHandlerTest {
    public TextHandler handler;

    @BeforeMethod
    public void initHandler() {
        handler = new TextHandler();
    }

    @DataProvider(name = "textProvider")
    public Object[][] provider1() {
        return new Object[][] {
                {"""
                    It has survived - not only (five) centuries, but also the leap into electronic
                typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига)
                with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and
                more recently with desktop publishing software like Aldus PageMaker Faclon9 including
                versions of Lorem Ipsum!
                    It is a long a!=b established fact that a reader will be distracted by the readable
                content of a page when looking at its layout. The point of using Ipsum is that it has a
                more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here),
                content here's, making it look like readable English?
                    It is a established fact that a reader will be of a page when looking at its layout...
                    Bye бандерлоги.
                """},
                {"""
                    It has survived - not only (five) centuries, but also the leap into 13<<2 electronic
                typesetting, remaining 3>>5 essentially ~6&9|(3&4) unchanged. It was popularised in the
                5|(1&2&(3|(4&(1^5|6&47)|3)|(~89&4|(42&7)))|1) with the release of Letraset sheets containing
                Lorem Ipsum passages, and more recently with desktop publishing software like Aldus
                PageMaker including versions of Lorem Ipsum.
                    It is a long established fact that a reader will be distracted by the readable content of a
                page when looking at its layout. The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78
                Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using (Content
                here), content here', making it look like readable English.
                    It is a (7^5|1&2<<(2|5>>2&71))|1200 established fact that a reader will be of a page
                when looking at its layout.
                    Bye.
                """}
        };
    }

    @Test(dataProvider = "textProvider")
    public void StringHandleRequestTest(String text) {
        TextComposite composite = handler.handleRequest(text);
        String actual = composite.restoreText().stripTrailing();
        String expected = text.stripTrailing();
        Assert.assertEquals(actual, expected);
    }
}
