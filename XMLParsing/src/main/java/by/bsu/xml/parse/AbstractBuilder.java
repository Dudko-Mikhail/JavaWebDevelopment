package by.bsu.xml.parse;

import by.bsu.xml.entity.Bank;
import by.bsu.xml.exception.CustomXmlParseException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBuilder {
    protected List<Bank> banks;

    protected AbstractBuilder() {
        banks = new ArrayList<>();
    }

    protected AbstractBuilder(List<Bank> banks) {
        this.banks = banks != null ? banks : new ArrayList<>();
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public abstract void buildBanks(String fileName) throws CustomXmlParseException;
}
