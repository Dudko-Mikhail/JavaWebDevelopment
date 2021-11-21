package by.bsu.informationhandling.constant;

public enum AdditionalComponentText {
    PARAGRAPH_TEXT(" ".repeat(4)),
    LEXEME(" "),
    PARAGRAPH_END("\n");

    private final String text;

    AdditionalComponentText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
