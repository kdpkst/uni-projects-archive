package lab8;

public enum Minion {
    KEVIN("Uh, la cucaracha?"),
    BOB("King Bob!"),
    STUART("Banana!");

    final String quote;

    Minion(String quote) {
        this.quote = quote;
    }

    public String getQuote() {
        return quote;
    }
}