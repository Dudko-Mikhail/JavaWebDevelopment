package by.bsu.xml.entity;

import by.bsu.xml.constant.DepositType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Currency;

public class Deposit extends MoneyStorage {
    private long accountId;
    private double profitability;
    private LocalDate depositTerm;
    private DepositType depositType;

    public Deposit() {
        super(new BigDecimal("0"), Currency.getInstance("USD"));
    }

    public Deposit(BigDecimal moneyAmount, Currency currency, long accountId,
                   double profitability, LocalDate depositTerm, DepositType depositType) {
        super(moneyAmount, currency);
        this.accountId = accountId;
        this.profitability = profitability;
        this.depositTerm = depositTerm;
        this.depositType = depositType;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public LocalDate getDepositTerm() {
        return depositTerm;
    }

    public void setDepositTerm(LocalDate depositTerm) {
        this.depositTerm = depositTerm;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Deposit)) return false;
        if (!super.equals(o)) return false;

        Deposit deposit = (Deposit) o;

        if (accountId != deposit.accountId) return false;
        if (Double.compare(deposit.profitability, profitability) != 0) return false;
        if (depositTerm != null ? !depositTerm.equals(deposit.depositTerm) : deposit.depositTerm != null) return false;
        return depositType == deposit.depositType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        result = 31 * result + (int) (accountId ^ (accountId >>> 32));
        temp = Double.doubleToLongBits(profitability);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (depositTerm != null ? depositTerm.hashCode() : 0);
        result = 31 * result + (depositType != null ? depositType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Deposit{");
        sb.append(super.toString());
        sb.append(", accountId=").append(accountId);
        sb.append(", profitability=").append(profitability);
        sb.append(", depositTerm=").append(depositTerm);
        sb.append(", depositType=").append(depositType);
        sb.append('}');
        return sb.toString();
    }
}
