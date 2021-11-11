package by.bsu.informationhandling.constant;

public enum AdditionalComponentText {
    PARAGRAPH_TEXT(" ".repeat(4)),
    LEXEME(" ");

    private final String text;

    AdditionalComponentText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
