package by.epam.pretask.utils;

import by.epam.pretask.exceptions.CustomNumberParseException;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void correctNumberTest() throws CustomNumberParseException {
        Validator.validate("45.9");
    }

    @Test(expected = CustomNumberParseException.class)
    public void incorrectNumberTest() throws CustomNumberParseException {
        Validator.validate("45,8");
    }
}
