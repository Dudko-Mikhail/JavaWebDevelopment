package by.bsu.xml.parse.sax;

import by.bsu.xml.entity.Bank;
import by.bsu.xml.parse.AbstractBuilder;

import java.util.List;

public class SaxBuilder extends AbstractBuilder {
    @Override
    public void buildBanks(String fileName) {

    }

    public SaxBuilder() {
    }

    public SaxBuilder(List<Bank> banks) {
        super(banks);
    }
}
