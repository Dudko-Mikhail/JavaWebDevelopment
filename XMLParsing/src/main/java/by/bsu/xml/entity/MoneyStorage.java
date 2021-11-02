package by.bsu.xml.entity;

import java.math.BigDecimal;
import java.util.Currency;

public class MoneyStorage {
    private BigDecimal moneyAmount;
    private Currency currency;

    public MoneyStorage(BigDecimal moneyAmount, Currency currency) {
        this.moneyAmount = moneyAmount;
        this.currency = currency;
    }

    public BigDecimal add(BigDecimal money) {
        return moneyAmount.add(money);
    }

    public BigDecimal multiply(BigDecimal multiplicand) {
        return moneyAmount.multiply(multiplicand);
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        MoneyStorage storage = (MoneyStorage) o;

        if (moneyAmount != null ? !moneyAmount.equals(storage.moneyAmount) : storage.moneyAmount != null) return false;
        return currency != null ? currency.equals(storage.currency) : storage.currency == null;
    }

    @Override
    public int hashCode() {
        int result = moneyAmount != null ? moneyAmount.hashCode() : 0;
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MoneyStorage{");
        sb.append("moneyAmount=").append(moneyAmount);
        sb.append(", currency=").append(currency);
        sb.append('}');
        return sb.toString();
    }
}
