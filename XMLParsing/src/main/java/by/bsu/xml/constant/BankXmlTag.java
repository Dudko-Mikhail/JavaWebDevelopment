package by.bsu.xml.constant;

public enum BankXmlTag {
    BANKS("banks"),
    BANK("bank"),
    NAME("name"),
    COUNTRY("country"),
    DEPOSITORS("depositors"),
    DEPOSITOR("depositor"),
    DEPOSITS("deposits"),
    DEPOSIT("deposit"),
    MONEY_AMOUNT("moneyAmount"),
    PROFITABILITY("profitability"),
    DEPOSIT_TERM("depositTerm"),
    DEPOSIT_TYPE("depositType");

    private final String value;

    BankXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
