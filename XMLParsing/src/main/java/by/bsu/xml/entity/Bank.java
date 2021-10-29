package by.bsu.xml.entity;

import java.util.HashSet;
import java.util.Set;

public class Bank {
    private String bankId;
    private String name;
    private String country;
    private Set<Depositor> depositors;


    public Bank() {
        depositors = new HashSet<>();
    }
    public Bank(String bankId, String name, String country) {
        this.bankId = bankId;
        this.name = name;
        this.country = country;
        depositors = new HashSet<>();
    }

    public Bank(String bankId, String name, String country, Set<Depositor> depositors) {
        this.bankId = bankId;
        this.name = name;
        this.country = country;
        this.depositors = depositors != null ? depositors : new HashSet<>();
    }

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Depositor> getDepositors() {
        return depositors;
    }

    public void setDepositors(Set<Depositor> depositors) {
        this.depositors = depositors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bank bank = (Bank) o;

        if (bankId != null ? !bankId.equals(bank.bankId) : bank.bankId != null) return false;
        if (name != null ? !name.equals(bank.name) : bank.name != null) return false;
        if (country != null ? !country.equals(bank.country) : bank.country != null) return false;
        return depositors != null ? depositors.equals(bank.depositors) : bank.depositors == null;
    }

    @Override
    public int hashCode() {
        int result = bankId != null ? bankId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (depositors != null ? depositors.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Bank{");
        sb.append("bankId='").append(bankId).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", depositors=").append(depositors);
        sb.append('}');
        return sb.toString();
    }
}
