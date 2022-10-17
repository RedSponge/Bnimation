package com.redsponge.bnimation.graph.conditions;

import com.redsponge.bnimation.graph.conditions.operation.Operation;

import java.util.Arrays;
import java.util.stream.Collectors;

public class OperatorCondition implements Condition {

    private final Condition[] conditions;
    private final Operation operation;

    public OperatorCondition(Operation operation, Condition... conditions) {
        this.conditions = conditions;
        this.operation = operation;
    }

    @Override
    public boolean test() {
        return operation.operate(conditions);
    }

    @Override
    public String toString() {
        return Arrays.stream(conditions).map(c -> "(" + c + ")").collect(Collectors.joining(" " + operation.toString() + " "));
    }

    public Condition[] getConditions() {
        return conditions;
    }

    public Operation getOperation() {
        return operation;
    }
}
