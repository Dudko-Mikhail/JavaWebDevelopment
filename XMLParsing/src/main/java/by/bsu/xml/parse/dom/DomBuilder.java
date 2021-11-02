package by.bsu.xml.parse.dom;

import by.bsu.xml.constant.BankXmlAttribute;
import by.bsu.xml.constant.BankXmlTag;
import by.bsu.xml.constant.DepositType;
import by.bsu.xml.entity.Bank;
import by.bsu.xml.entity.Deposit;
import by.bsu.xml.entity.Depositor;
import by.bsu.xml.exception.CustomXmlParseException;
import by.bsu.xml.parse.AbstractBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DomBuilder extends AbstractBuilder {
    private static final Logger logger = LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) { // impossible
            logger.log(Level.ERROR, e);
        }
    }

    public DomBuilder(List<Bank> banks) {
        super(banks);
    }

    public DomBuilder() {}

    @Override
    public void buildBanks(String fileName) throws CustomXmlParseException {
        try {
            Document document = documentBuilder.parse(fileName);
            Element root = document.getDocumentElement();
            NodeList banksList = root.getElementsByTagName(BankXmlTag.BANK.getValue());
            for (int i = 0; i < banksList.getLength(); i++) {
                Element currentBank = (Element) banksList.item(i);
                banks.add(buildBank(currentBank));
            }
        } catch (SAXException | IOException e) {
           throw new CustomXmlParseException(e);
        }
    }

    private Bank buildBank(Element element) {
        Bank bank = new Bank();
        String bankId = element.getAttribute(BankXmlAttribute.BANK_ID.getValue());
        bank.setBankId(bankId);
        bank.setName(extractElementTextContent(element, BankXmlTag.NAME.getValue()));
        bank.setCountry(extractElementTextContent(element, BankXmlTag.COUNTRY.getValue()));
        NodeList depositorList = element.getElementsByTagName(BankXmlTag.DEPOSITOR.getValue());
        Set<Depositor> depositors = buildDepositors(depositorList);
        bank.setDepositors(depositors);
        return bank;
    }

    private Set<Depositor> buildDepositors(NodeList depositorList) {
        Set<Depositor> depositors = new HashSet<>();
        for (int i = 0; i < depositorList.getLength(); i++) {
            Element depositorElement = (Element) depositorList.item(i);
            String name = depositorElement.getAttribute(BankXmlAttribute.NAME.getValue());
            Depositor depositor = new Depositor(name);
            NodeList depositList = depositorElement.getElementsByTagName(BankXmlTag.DEPOSIT.getValue());
            depositor.setDeposits(buildDeposits(depositList));
            depositors.add(depositor);
        }
        return depositors;
    }

    private Set<Deposit> buildDeposits(NodeList depositList) {
        Set<Deposit> deposits = new HashSet<>();
        for (int i = 0; i < depositList.getLength(); i++) {
            Element depositElement = (Element) depositList.item(i);
            Deposit deposit = new Deposit();
            long accountId = Long.parseLong(depositElement.getAttribute(BankXmlAttribute.ACCOUNT_ID.getValue()));
            deposit.setAccountId(accountId);
            String currencyCode = depositElement.getAttribute(BankXmlAttribute.CURRENCY.getValue());
            if (currencyCode != null && !currencyCode.isEmpty()) {
                deposit.setCurrency(Currency.getInstance(currencyCode));
            }
            String moneyAmount = extractElementTextContent(depositElement, BankXmlTag.MONEY_AMOUNT.getValue());
            deposit.setMoneyAmount(new BigDecimal(moneyAmount));
            String profitability = extractElementTextContent(depositElement, BankXmlTag.PROFITABILITY.getValue());
            deposit.setProfitability(Double.parseDouble(profitability));
            DepositType type = DepositType.valueOf(extractElementTextContent(depositElement, BankXmlTag.DEPOSIT_TYPE.getValue()));
            deposit.setDepositType(type);
            String depositTerm = extractElementTextContent(depositElement, BankXmlTag.DEPOSIT_TERM.getValue());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(depositTerm, formatter);
            deposit.setDepositTerm(date);
            deposits.add(deposit);
        }
        return deposits;
    }

    private String extractElementTextContent(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }
}
