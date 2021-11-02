package by.bsu.xml.validator;

import by.bsu.xml.exception.CustomXmlParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class XsdValidatorXml {
    private static final Logger logger = LogManager.getLogger();
    private Validator validator;

    public XsdValidatorXml(String xsdFilePath) throws CustomXmlParseException {
        if (!Files.exists(Path.of(xsdFilePath))) {
            throw new CustomXmlParseException("Не удаётся найти указанный файл" + xsdFilePath);
        }
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = factory.newSchema(new StreamSource(xsdFilePath));
            validator = schema.newValidator();
        } catch (SAXException e) {
            throw new CustomXmlParseException(e);
        }
    }

    public void validate(String xmlFilePath) throws CustomXmlParseException {
        if (!Files.exists(Path.of(xmlFilePath))) {
            throw new CustomXmlParseException("Не удаётся найти указанный файл" + xmlFilePath);
        }
        try {
            validator.validate(new StreamSource(xmlFilePath));
        } catch (SAXException | IOException e) {
            throw new CustomXmlParseException(e);
        }
        logger.info("XML file is valid");
    }
}
