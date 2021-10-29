package by.bsu.xml.parse.sax;

import by.bsu.xml.constant.BankXmlAttribute;
import by.bsu.xml.constant.BankXmlTag;
import by.bsu.xml.constant.DepositType;
import by.bsu.xml.entity.Bank;
import by.bsu.xml.entity.Deposit;
import by.bsu.xml.entity.Depositor;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class BankHandler extends DefaultHandler {
    private List<Bank> banks;
    private BankXmlTag currentXmlTag;
    private Bank currentBank;
    private Set<Deposit> deposits;
    private Set<Depositor> depositors;
    private Depositor currentDepositor;
    private Deposit currentDeposit;

    public BankHandler() {
        banks = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentXmlTag = extractBankXmlTagFromTagName(qName);
        switch (currentXmlTag) {
            case BANK -> {
                currentBank = new Bank();
                String bankId = attributes.getValue(BankXmlAttribute.BANK_ID.getValue());
                currentBank.setBankId(bankId);
            }
            case DEPOSITORS -> depositors = new HashSet<>();
            case DEPOSITOR -> {
                currentDepositor = new Depositor(attributes.getValue(BankXmlAttribute.NAME.getValue()));
            }
            case DEPOSITS -> deposits = new HashSet<>();
            case DEPOSIT -> {
                currentDeposit = new Deposit();
                String accountId = attributes.getValue(BankXmlAttribute.ACCOUNT_ID.getValue());
                String currencyCode = attributes.getValue(BankXmlAttribute.CURRENCY.getValue());
                if (currencyCode != null && !currencyCode.isEmpty()) {
                    currentDeposit.setCurrency(Currency.getInstance(currencyCode));
                }
                currentDeposit.setAccountId(Long.parseLong(accountId));
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        BankXmlTag endTag = extractBankXmlTagFromTagName(qName);
        switch (endTag) {
            case BANK -> banks.add(currentBank);
            case DEPOSITORS -> currentBank.setDepositors(depositors);
            case DEPOSITOR -> depositors.add(currentDepositor);
            case DEPOSITS -> currentDepositor.setDeposits(deposits);
            case DEPOSIT -> deposits.add(currentDeposit);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String value = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case NAME -> currentBank.setName(value);
                case COUNTRY -> currentBank.setCountry(value);
                case MONEY_AMOUNT -> {
                    currentDeposit.setMoneyAmount(new BigDecimal(value));
                }
                case PROFITABILITY -> currentDeposit.setProfitability(Double.parseDouble(value));
                case DEPOSIT_TERM -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate date = LocalDate.parse(value, formatter);
                    currentDeposit.setDepositTerm(date);
                }
                case DEPOSIT_TYPE -> currentDeposit.setDepositType(DepositType.valueOf(value));
            }
            currentXmlTag = null;
        }
    }

    private BankXmlTag extractBankXmlTagFromTagName(String tag) {
        return switch (tag) {
            case "moneyAmount" -> BankXmlTag.MONEY_AMOUNT;
            case "depositTerm" -> BankXmlTag.DEPOSIT_TERM;
            case "depositType" -> BankXmlTag.DEPOSIT_TYPE;
            default -> BankXmlTag.valueOf(tag.toUpperCase());
        };
    }

    public List<Bank> getBanks() {
        return banks;
    }
}