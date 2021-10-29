package by.bsu.xml.parse.sax;

import by.bsu.xml.entity.Bank;
import by.bsu.xml.exception.CustomXmlParseException;
import by.bsu.xml.parse.AbstractBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.List;

public class SaxBuilder extends AbstractBuilder {
    private final Logger logger = LogManager.getLogger();
    private XMLReader xmlReader;
    private BankHandler handler = new BankHandler();

    {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            xmlReader = parser.getXMLReader();
            xmlReader.setContentHandler(handler);
        } catch (ParserConfigurationException | SAXException e) {
           logger.log(Level.ERROR, e);
        }
    }

    public SaxBuilder() {
    }

    public SaxBuilder(List<Bank> banks) {
        super(banks);
    }

    @Override
    public void buildBanks(String fileName) throws CustomXmlParseException {
        try {
            xmlReader.parse(fileName);
        } catch (IOException | SAXException e) {
            throw new CustomXmlParseException(e);
        }
        banks = handler.getBanks();
    }
}
