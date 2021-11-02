package by.bsu.xml.parse.validator;

import by.bsu.xml.exception.CustomXmlParseException;
import by.bsu.xml.validator.XsdValidatorXml;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class XsdValidatorXmlTest {
    XsdValidatorXml validatorXml;

    @BeforeMethod
    public void initValidator() throws CustomXmlParseException {
        validatorXml = new XsdValidatorXml("src/test/resources/data/banks.xsd");
    }

    @Test
    public void validateTestWithCorrectData() throws CustomXmlParseException {
        validatorXml.validate("src/test/resources/data/correctBanks.xml");
    }

    @Test(expectedExceptions = CustomXmlParseException.class)
    public void validateTestWithIncorrectData() throws CustomXmlParseException {
        validatorXml.validate("src/test/resources/data/incorrectBanks.xml");
    }
}
