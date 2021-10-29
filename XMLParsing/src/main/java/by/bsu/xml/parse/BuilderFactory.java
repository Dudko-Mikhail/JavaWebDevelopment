package by.bsu.xml.parse;

import by.bsu.xml.constant.BuilderType;
import by.bsu.xml.entity.Bank;
import by.bsu.xml.parse.dom.DomBuilder;
import by.bsu.xml.parse.sax.SaxBuilder;
import by.bsu.xml.parse.stax.StaxBuilder;

import java.util.List;
import java.util.Optional;

public class BuilderFactory {
    private static BuilderFactory INSTANCE;

    public static BuilderFactory getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BuilderFactory();
        }
        return INSTANCE;
    }

    public Optional<AbstractBuilder> createBuilder(BuilderType type, List<Bank> banks) {
        if (type == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(switch (type) {
            case DOM -> new DomBuilder(banks);
            case SAX -> new SaxBuilder(banks);
            case STAX -> new StaxBuilder(banks);
            default -> null;
        });
    }

    public Optional<AbstractBuilder> createBuilder(BuilderType type) {
        return createBuilder(type, null);
    }

    private BuilderFactory() {
    }
}
