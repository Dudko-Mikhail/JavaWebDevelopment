package by.bsu.informationhandling.constant;

public enum ComponentRegex {
    PARAGRAPH(" ".repeat(4)),
    SENTENCE("(.|\n)+?([?!.]|[.]{3})([\n ]|\\z)"),
    LEXEME(" ");

    private final String regex;
    ComponentRegex(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
