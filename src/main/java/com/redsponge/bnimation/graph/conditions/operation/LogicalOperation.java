package com.redsponge.bnimation.graph.conditions.operation;

import com.redsponge.bnimation.graph.conditions.Condition;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum LogicalOperation implements Operation {

    AND((a, b) -> a && b, "&&"),
    OR((a, b) -> a || b, "||"),
    XOR((a, b) -> a ^ b, "^"),

    ;

    private final BinaryOperator<Boolean> operator;
    private final String repr;

    LogicalOperation(BinaryOperator<Boolean> operator, String repr) {
        this.operator = operator;
        this.repr = repr;
    }

    @Override
    public boolean operate(Condition... conditions) {
        return Arrays.stream(conditions).map(Condition::test).reduce(operator).orElse(false);
    }

    @Override
    public String toString() {
        return repr;
    }
}
