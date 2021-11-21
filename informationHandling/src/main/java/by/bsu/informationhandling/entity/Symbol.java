package by.bsu.informationhandling.entity;

import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.constant.SymbolType;

public class Symbol extends AbstractComponent {
    private char value;
    private SymbolType symbolType;

    public Symbol(char value) {
        super(ComponentType.SYMBOL);
        this.value = value;
        symbolType = Symbol.defineSymbolType(value);
    }

    @Override
    public String restoreText() {
        return String.valueOf(value);
    }

    public char getValue() {
        return value;
    }

    public void setValue(char value) {
        this.value = value;
        symbolType = Symbol.defineSymbolType(value);
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    public static SymbolType defineSymbolType(char symbol) {
        String symbolString = String.valueOf(symbol);
        SymbolType[] symbolTypes = SymbolType.values();
        for (SymbolType symbolType: symbolTypes) {
            if (symbolString.matches(symbolType.getRegex())) {
                return symbolType;
            }
        }
        return SymbolType.UNDEFINED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol1 = (Symbol) o;

        if (value != symbol1.value) return false;
        return symbolType == symbol1.symbolType;
    }

    @Override
    public int hashCode() {
        int result = value;
        result = 31 * result + symbolType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbol{");
        sb.append("symbol=").append(value);
        sb.append(", symbolType=").append(symbolType);
        sb.append('}');
        return sb.toString();
    }
}
