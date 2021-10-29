package by.bsu.xml.parse.stax;

import by.bsu.xml.constant.BankXmlAttribute;
import by.bsu.xml.constant.BankXmlTag;
import by.bsu.xml.constant.DepositType;
import by.bsu.xml.entity.Bank;
import by.bsu.xml.entity.Deposit;
import by.bsu.xml.entity.Depositor;
import by.bsu.xml.exception.CustomXmlParseException;
import by.bsu.xml.parse.AbstractBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaxBuilder extends AbstractBuilder {
    private final Logger logger = LogManager.getLogger();
    private final XMLInputFactory factory;

    {
        factory = XMLInputFactory.newFactory();
    }

    public StaxBuilder() {
        super();
    }

    public StaxBuilder(List<Bank> banks) {
        super(banks);
    }

    @Override
    public void buildBanks(String fileName) throws CustomXmlParseException {
        try {
            Path path = Path.of(fileName);
            XMLStreamReader reader = factory.createXMLStreamReader(Files.newBufferedReader(path));
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    String name = reader.getLocalName();
                    if (name.equals(BankXmlTag.BANK.getValue())) {
                        Bank bank = buildBank(reader);
                        banks.add(bank);
                    }
                }
            }
        } catch (XMLStreamException | IOException e) {
            logger.catching(e);
            throw new CustomXmlParseException(e);
        }
    }

    private Bank buildBank(XMLStreamReader reader) throws XMLStreamException {
        Bank bank = new Bank();
        String bankId = reader.getAttributeValue(null, BankXmlAttribute.BANK_ID.getValue());
        bank.setBankId(bankId);
        BankXmlTag currentXmlTag;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    String name = reader.getLocalName();
                    currentXmlTag = extractBankXmlTagFromTagName(name);
                    switch (currentXmlTag) {
                        case NAME -> bank.setName(extractXMLTagTextContent(reader));
                        case COUNTRY -> bank.setCountry(extractXMLTagTextContent(reader));
                        case DEPOSITORS -> bank.setDepositors(buildDepositors(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if (name.equals(BankXmlTag.BANK.getValue())) {
                        return bank;
                    }
                }
            }

        }
        throw new XMLStreamException("Unknown element in tag <bank>");
    }

    private BankXmlTag extractBankXmlTagFromTagName(String tag) {
        return switch (tag) {
            case "moneyAmount" -> BankXmlTag.MONEY_AMOUNT;
            case "depositTerm" -> BankXmlTag.DEPOSIT_TERM;
            case "depositType" -> BankXmlTag.DEPOSIT_TYPE;
            default -> BankXmlTag.valueOf(tag.toUpperCase());
        };
    }

    private String extractXMLTagTextContent(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        while (reader.hasNext()) {
            int type = reader.next();
            if (type == XMLStreamConstants.CHARACTERS) {
                text = reader.getText();
                break;
            }
        }
        return text;
    }

    private Set<Depositor> buildDepositors(XMLStreamReader reader) throws XMLStreamException {
        Set<Depositor> depositors = new HashSet<>();
        Depositor depositor = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    String name = reader.getLocalName();
                    BankXmlTag currentXmlTag = extractBankXmlTagFromTagName(name);
                    switch (currentXmlTag) { // TODO  отдельный метод
                        case DEPOSITOR -> depositor = new Depositor("");
                        case NAME -> depositor.setName(extractXMLTagTextContent(reader));
                        case DEPOSITS -> depositor.setDeposits(buildDeposits(reader));
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if (name.equals(BankXmlTag.DEPOSITOR.getValue())) {
                        depositors.add(depositor);
                        depositor = null;
                    }
                    else if (name.equals(BankXmlTag.DEPOSITORS.getValue())) {
                        return depositors;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <depositors>");
    }

    private Set<Deposit> buildDeposits(XMLStreamReader reader) throws XMLStreamException {
        Set<Deposit> deposits = new HashSet<>();
        Deposit deposit = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT -> {
                    String name = reader.getLocalName();
                    BankXmlTag currentXmlTag = extractBankXmlTagFromTagName(name);
                    switch (currentXmlTag) {
                        case DEPOSIT -> { // TODO  отдельный метод
                            deposit = new Deposit();
                            String accountId = reader.getAttributeValue(null, BankXmlAttribute.ACCOUNT_ID.getValue());
                            String currencyCode = reader.getAttributeValue(null, BankXmlAttribute.CURRENCY.getValue());
                            deposit.setAccountId(Long.parseLong(accountId));
                            if (currencyCode != null) {
                                deposit.setCurrency(Currency.getInstance(currencyCode));
                            }
                        }
                        case MONEY_AMOUNT -> {
                            String value = extractXMLTagTextContent(reader);
                            deposit.setMoneyAmount(new BigDecimal(value));
                        }
                        case PROFITABILITY -> {
                            String value = extractXMLTagTextContent(reader);
                            deposit.setProfitability(Double.parseDouble(value));
                        }
                        case DEPOSIT_TERM -> {
                            String value = extractXMLTagTextContent(reader);
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            LocalDate date = LocalDate.parse(value, formatter);
                            deposit.setDepositTerm(date);
                        }
                        case DEPOSIT_TYPE -> {
                            String value = extractXMLTagTextContent(reader);
                            deposit.setDepositType(DepositType.valueOf(value));
                        }
                    }
                }
                case XMLStreamConstants.END_ELEMENT -> {
                    String name = reader.getLocalName();
                    if (name.equals(BankXmlTag.DEPOSIT.getValue())) {
                        deposits.add(deposit);
                        deposit = null;
                    }
                    else if (name.equals(BankXmlTag.DEPOSITS.getValue())) {
                        return deposits;
                    }
                }
            }
        }
        throw new XMLStreamException("Unknown element in tag <deposits>");
    }
}
