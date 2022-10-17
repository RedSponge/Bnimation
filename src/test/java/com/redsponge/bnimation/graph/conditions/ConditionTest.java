package com.redsponge.bnimation.graph.conditions;

import com.redsponge.bnimation.TestUtils;
import com.redsponge.bnimation.graph.conditions.comparison.Comparison;
import com.redsponge.bnimation.graph.conditions.comparison.Comparisons;
import com.redsponge.bnimation.graph.conditions.operation.LogicalOperation;
import com.redsponge.bnimation.graph.conditions.operation.Operation;
import com.redsponge.bnimation.graph.value.ConstantValue;
import com.redsponge.bnimation.graph.value.GraphValueMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class ConditionTest {

    private static final HashMap<Operation, BinaryOperator<Boolean>> combiners = new HashMap<>() {{
        put(LogicalOperation.AND, (a, b) -> a && b);
        put(LogicalOperation.OR, (a, b) -> a || b);
        put(LogicalOperation.XOR, (a, b) -> a ^ b);
    }};

    private static final HashMap<Comparison, Function<Integer, Boolean>> comparators = new HashMap<>() {{
        put(Comparisons.LESSER_THAN, n -> n < 0);
        put(Comparisons.LESSER_EQUALS, n -> n <= 0);
        put(Comparisons.EQUALS, n -> n == 0);
        put(Comparisons.GREATER_EQUALS, n -> n >= 0);
        put(Comparisons.GREATER_THAN, n -> n > 0);
        put(Comparisons.NOT_EQUAL, n -> n != 0);
    }};

    private static Stream<Arguments> conditions() {
        List<Arguments> conditions = new ArrayList<>();

        conditions.add(arguments(BooleanCondition.TRUE, true));
        conditions.add(arguments(BooleanCondition.FALSE, false));

        Boolean[] bools = {false, true};
        Object[][] cartes = TestUtils.cartesianProduct(new Boolean[][]{bools, bools, bools});

        for (Operation operation : combiners.keySet()) {
            for (Object[] combination : cartes) {
                boolean expected = Arrays.stream(combination).map(m -> (Boolean) m).reduce(combiners.get(operation)).orElse(false);
                conditions.add(arguments(new OperatorCondition(operation, Arrays.stream(combination).map(m -> BooleanCondition.get((Boolean) m)).toArray(Condition[]::new)), expected));
            }
        }

        conditions.add(arguments(
                new OperatorCondition(
                        LogicalOperation.OR,
                        new OperatorCondition(LogicalOperation.AND, BooleanCondition.FALSE, BooleanCondition.FALSE),
                        new OperatorCondition(LogicalOperation.AND, BooleanCondition.TRUE, BooleanCondition.TRUE)
                ), true));

        conditions.add(arguments(
                new NotCondition(
                        BooleanCondition.TRUE
                ), false
        ));

        conditions.add(arguments(
                new NotCondition(
                        BooleanCondition.FALSE
                ), true
        ));

        int a = 1;
        int b = 3;
        for (Comparison comparison : comparators.keySet()) {
            conditions.add(arguments(
                    new ComparisonCondition<>(
                            comparison,
                            Integer::compare,
                            ConstantValue.intValue(a),
                            ConstantValue.intValue(b)
                    ), comparators.get(comparison).apply(Integer.compare(a, b))
            ));
        }

        return conditions.stream();
    }

    @ParameterizedTest
    @MethodSource("conditions")
    void testCondition(Condition condition, boolean expectedOutcome) {
        assertEquals(expectedOutcome, condition.test());
    }

    private static Stream<Arguments> conditionStrings() {
        GraphValueMap valueMap = new GraphValueMap();

        return Stream.of(
                arguments(new ComparisonCondition<>(Comparisons.EQUALS, Integer::compare, ConstantValue.intValue(1), ConstantValue.intValue(2)), "1 == 2"),
                arguments(new ComparisonCondition<>(Comparisons.LESSER_THAN, Float::compare, ConstantValue.floatValue(0), ConstantValue.floatValue(1.5f)), "0.0 < 1.5"),
                arguments(new ComparisonCondition<>(Comparisons.NOT_EQUAL, Boolean::compare, valueMap.getBooleanRef("is_on_ground"), ConstantValue.booleanValue(false)), "var[is_on_ground] != false"),
                arguments(new OperatorCondition(LogicalOperation.AND, BooleanCondition.FALSE, BooleanCondition.TRUE, BooleanCondition.FALSE), "(false) && (true) && (false)"),
                arguments(new OperatorCondition(LogicalOperation.AND,
                            new ComparisonCondition<>(Comparisons.GREATER_EQUALS, Integer::compare, ConstantValue.intValue(2), ConstantValue.intValue(5)),
                            new OperatorCondition(LogicalOperation.OR, BooleanCondition.TRUE, BooleanCondition.FALSE)
                        ), "(2 >= 5) && ((true) || (false))")
        );
    }

    @ParameterizedTest
    @MethodSource("conditionStrings")
    void testConditionToString(Condition c, String expected) {
        assertEquals(expected, c.toString());
    }

}
