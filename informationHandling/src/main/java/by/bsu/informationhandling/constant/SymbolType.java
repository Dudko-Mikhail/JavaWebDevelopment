package by.bsu.informationhandling.constant;

public enum SymbolType {
    NEW_LINE("\n"),
    SPACE("\\s"),
    VOWEL_ENGLISH("(?i)[aeiouy]"),
    VOWEL_RUSSIAN("(?ui)[ауоыиэяюёе]"),
    CONSONANT_ENGLISH("(?i)[bcdfghjklmnpqrstvwxyz]"),
    CONSONANT_RUSSIAN("(?ui)[бвгджзйклмнпрстфхцчшщ]"),
    SIGN_RUSSIAN("(?ui)[ъь]"),
    DIGIT("\\d"),
    PUNCTUATION("\\p{Punct}"),
    UNDEFINED("");

    private final String regex;
    SymbolType(String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
