package by.bsu.xml.constant;

public enum BankXmlAttribute {
    BANK_ID("bankId"),
    ACCOUNT_ID("accountId"),
    CURRENCY("currency");

    private final String value;

    BankXmlAttribute(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
