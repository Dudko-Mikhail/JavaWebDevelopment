package by.bsu.informationhandling.entity;

import by.bsu.informationhandling.constant.ComponentType;
import by.bsu.informationhandling.constant.SymbolType;

public class Symbol extends AbstractComponent {
    private char symbol;
    private SymbolType symbolType;

    public Symbol(char symbol) {
        super(ComponentType.SYMBOL);
        this.symbol = symbol;
        defineSymbolType();
    }

    @Override
    public String restoreText() {
        return String.valueOf(symbol);
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
        defineSymbolType();
    }

    public SymbolType getSymbolType() {
        return symbolType;
    }

    private void defineSymbolType() {
        String symbolString = String.valueOf(symbol).toLowerCase();
        SymbolType[] symbolTypes = SymbolType.values();
        for (SymbolType symbolType: symbolTypes) {
            if (symbolString.matches(symbolType.getRegex())) {
                this.symbolType = symbolType;
            }
        }
        if (symbolType == null) { // TODO Исключение либо SymbolType.UNDEFINED
            symbolType = SymbolType.UNDEFINED;
//            throw new EnumConstantNotPresentException(SymbolType.class , symbolString);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol1 = (Symbol) o;

        if (symbol != symbol1.symbol) return false;
        return symbolType == symbol1.symbolType;
    }

    @Override
    public int hashCode() {
        int result = symbol;
        result = 31 * result + symbolType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Symbol{");
        sb.append("symbol=").append(symbol);
        sb.append(", symbolType=").append(symbolType);
        sb.append('}');
        return sb.toString();
    }
}
