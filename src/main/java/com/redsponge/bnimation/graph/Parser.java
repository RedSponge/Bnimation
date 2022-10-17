package com.redsponge.bnimation.graph;

import com.badlogic.gdx.utils.JsonValue;
import com.redsponge.bnimation.graph.conditions.BooleanCondition;
import com.redsponge.bnimation.graph.conditions.NotCondition;
import com.redsponge.bnimation.graph.conditions.comparison.Comparison;
import com.redsponge.bnimation.graph.conditions.ComparisonCondition;
import com.redsponge.bnimation.graph.conditions.Condition;
import com.redsponge.bnimation.graph.conditions.comparison.Comparisons;
import com.redsponge.bnimation.graph.value.ConstantValue;
import com.redsponge.bnimation.graph.value.Value;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Parser {

    private final Map<String, Function<JsonValue, Condition>> conditionParsers;
    private final Map<String, Function<JsonValue, Value<?>>> valueParsers;

    public Parser() {
        this.conditionParsers = new HashMap<>();

        this.conditionParsers.put("comparison", this::parseComparisonCondition);
        this.conditionParsers.put("logic", this::parseLogicalCondition);
        this.conditionParsers.put("not", this::parseNotCondition);
        this.conditionParsers.put("constant", this::parseConstantCondition);

        this.valueParsers = new HashMap<>();
        this.valueParsers.put("value", this::parseConstantValue);
//        this.valueParsers.put("variable", this::parseVariable);
    }

    private Condition parseConstantCondition(JsonValue jsonValue) {
        return BooleanCondition.get(jsonValue.get("value").asBoolean());
    }

    public Graph parse(JsonValue root) {
        Graph output = new Graph();
        JsonValue nodes = root.get("nodes");

        for (JsonValue jsonNode : nodes) {
            Node node = output.addNode(jsonNode.name);
            JsonValue jsonLinks = jsonNode.get("links");
            for(JsonValue jsonLink : jsonLinks) {
                node.addLink(parseLink(jsonLink));
            }
        }

        return output;
    }

    private Link parseLink(JsonValue jsonLink) {
        String destination = jsonLink.getString("destination");
        Condition condition = parseCondition(jsonLink.get("condition"));
        return new Link(destination, condition);
    }

    private Condition parseCondition(JsonValue condition) {
        return this.conditionParsers.get(condition.getString("type")).apply(condition);
    }

    private Condition parseNotCondition(JsonValue conditionJson) {
        Condition condition = parseCondition(conditionJson.get("condition"));
        return new NotCondition(condition);
    }

    private Condition parseComparisonCondition(JsonValue condition) {
        String comparisonType = condition.getString("comparison");

        Comparison comparison = Comparisons.getBySymbolOrName(comparisonType);

        Value<?> value1 = parseValue(condition.get("value_1"));
        Value<?> value2 = parseValue(condition.get("value_2"));

        return new ComparisonCondition(comparison, getValueComparator(value1), value1, value2);
    }

    private Value<?> parseValue(JsonValue value) {
        return this.valueParsers.get(value.getString("type")).apply(value);
    }

    private Value<?> parseConstantValue(JsonValue value) {
        value = value.get("value"); // Unwrap json

        if(value.isBoolean()) return ConstantValue.booleanValue(value.asBoolean());
        if(value.isDouble()) return ConstantValue.floatValue(value.asFloat());
        if(value.isLong()) return ConstantValue.intValue(value.asInt());

        throw new RuntimeException("Constant value type not supported: " + value.type());
    }

    private Comparator<?> getValueComparator(Value<?> value) {
        Object val = value.get();

        if(val instanceof Integer) {
            return (Comparator<Integer>) Integer::compare;
        }
        if(val instanceof Float) {
            return (Comparator<Float>) Float::compare;
        }
        if(val instanceof Boolean) {
            return (Comparator<Boolean>) Boolean::compare;
        }

        throw new RuntimeException("Unknown comparator for value " + val);
    }

    private Condition parseLogicalCondition(JsonValue condition) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }


}
