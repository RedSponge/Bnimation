package com.redsponge.bnimation.graph.conditions.comparison;

public enum Comparisons implements Comparison {

    EQUALS("==", (n) -> n == 0),
    LESSER_THAN("<", (n) -> n < 0),
    GREATER_THAN(">", (n) -> n > 0),
    LESSER_EQUALS("<=", (n) -> n <= 0),
    GREATER_EQUALS(">=", (n) -> n >= 0),
    NOT_EQUAL("!=", (n) -> n != 0),
    ;

    Comparisons(String symbol, Comparison comparison) {
        this.symbol = symbol;
        this.comparison = comparison;
    }

    private final String symbol;
    private final Comparison comparison;
    @Override
    public boolean resolve(int comparatorResult) {
        return comparison.resolve(comparatorResult);
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    public static Comparisons getBySymbolOrName(String symbol) {
        for (Comparisons value : Comparisons.values()) {
            if(value.symbol.equals(symbol) || symbol.toLowerCase().equals(value.name().toLowerCase())) return value;
        }
        throw new RuntimeException("Unknown comparison for symbol " + symbol);
    }
}
