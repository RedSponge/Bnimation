package com.redsponge.bnimation.graph.conditions;

import com.redsponge.bnimation.graph.conditions.comparison.Comparison;
import com.redsponge.bnimation.graph.value.Value;

import java.util.Comparator;

public class ComparisonCondition<T> implements Condition {

    private final Value<T> a, b;
    private final Comparator<T> comparator;
    private final Comparison comparison;

    public ComparisonCondition(Comparison comparison, Comparator<T> comparator, Value<T> a, Value<T> b) {
        this.comparator = comparator;
        this.a = a;
        this.b = b;
        this.comparison = comparison;
    }

    @Override
    public boolean test() {
        return comparison.resolve(comparator.compare(a.get(), b.get()));
    }

    public Value<T> getA() {
        return a;
    }

    public Value<T> getB() {
        return b;
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public Comparison getComparison() {
        return comparison;
    }

    @Override
    public String toString() {
        return a.toString() + " " + comparison.toString() + " " + b.toString();
    }
}
