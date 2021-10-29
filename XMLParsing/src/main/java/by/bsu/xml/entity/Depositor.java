package by.bsu.xml.entity;

import java.util.HashSet;
import java.util.Set;

public class Depositor {
    private String name;
    private Set<Deposit> deposits;

    public Depositor(String name, Set<Deposit> deposits) {
        this.name = name;
        this.deposits = deposits != null ? deposits : new HashSet<>();
    }

    public Depositor(String name) {
        this.name = name;
        deposits = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<Deposit> deposits) {
        this.deposits = deposits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Depositor depositor = (Depositor) o;

        if (name != null ? !name.equals(depositor.name) : depositor.name != null) return false;
        return deposits != null ? deposits.equals(depositor.deposits) : depositor.deposits == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (deposits != null ? deposits.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Depositor{");
        sb.append("name='").append(name).append('\'');
        sb.append(", deposits=").append(deposits);
        sb.append('}');
        return sb.toString();
    }
}
