package by.bsu.xml.parse.dom;

import by.bsu.xml.entity.Bank;
import by.bsu.xml.parse.AbstractBuilder;

import java.util.List;

public class DomBuilder extends AbstractBuilder {
    @Override
    public void buildBanks(String fileName) {

    }

    public DomBuilder(List<Bank> banks) {
        super(banks);
    }

    public DomBuilder() {

    }
}
