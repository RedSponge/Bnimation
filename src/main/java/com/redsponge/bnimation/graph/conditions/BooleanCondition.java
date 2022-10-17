package com.redsponge.bnimation.graph.conditions;

public enum BooleanCondition implements Condition {
    TRUE(true),
    FALSE(false);


    private final boolean value;

    BooleanCondition(boolean value) {
        this.value = value;
    }

    public static BooleanCondition get(boolean value) {
        return value ? TRUE : FALSE;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean test() {
        return value;
    }
}
