package by.bsu.informationhandling.constant;

public enum SymbolType {
    NEW_LINE("\n"),
    SPACE("\\s"),
    VOWEL_ENGLISH("[aeiouy]"),
    VOWEL_RUSSIAN("[ауоыиэяюёе]"),
    CONSONANT_ENGLISH("[bcdfghjklmnpqrstvwxyz]"),
    CONSONANT_RUSSIAN("[бвгджзйклмнпрстфхцчшщ]"),
    SIGN_RUSSIAN("[ъь]"),
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
