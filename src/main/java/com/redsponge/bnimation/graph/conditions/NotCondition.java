package com.redsponge.bnimation.graph.conditions;

public class NotCondition implements Condition {

    private final Condition condition;

    public NotCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public boolean test() {
        return !condition.test();
    }

    @Override
    public String toString() {
        return "!(" + condition + ")";
    }

    public Condition getCondition() {
        return condition;
    }
}
