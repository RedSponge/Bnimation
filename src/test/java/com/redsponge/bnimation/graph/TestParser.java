package com.redsponge.bnimation.graph;

import com.badlogic.gdx.utils.JsonReader;
import com.redsponge.bnimation.TestUtils;
import com.redsponge.bnimation.graph.conditions.*;
import com.redsponge.bnimation.graph.conditions.comparison.Comparisons;
import com.redsponge.bnimation.graph.conditions.operation.LogicalOperation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TestParser {

    @Test
    void testParserComparisonCondition() {
        Parser p = new Parser();
        Graph g = p.parse(new JsonReader().parse(TestUtils.readFile("src/test/resources/comparison_graph.json")));

        Condition condition = g.getNode("a").getLink("b").orElseThrow().getCondition();

        assertInstanceOf(ComparisonCondition.class, condition);
        assertEquals(Comparisons.EQUALS, ((ComparisonCondition<?>) condition).getComparison());
    }

    @Test
    void testParserNotCondition() {
        Parser p = new Parser();
        Graph g = p.parse(new JsonReader().parse(TestUtils.readFile("src/test/resources/not_graph.json")));

        Condition condition = g.getNode("a").getLink("b").orElseThrow().getCondition();

        assertInstanceOf(NotCondition.class, condition);
        NotCondition notCondition = (NotCondition) condition;

        assertEquals(notCondition.getCondition(), BooleanCondition.FALSE);
    }

    @Test
    void testParserConstantCondition() {
        Parser p = new Parser();
        Graph g = p.parse(new JsonReader().parse(TestUtils.readFile("src/test/resources/constant_condition.json")));

        Condition c = g.getNode("a").getLink("b").orElseThrow().getCondition();

        assertEquals(c, BooleanCondition.TRUE);
    }

    @Test
    void testParserLogicGraph() {
        Parser p = new Parser();
        Graph g = p.parse(new JsonReader().parse(TestUtils.readFile("src/test/resources/logic_graph.json")));

        Condition c = g.getNode("a").getLink("b").orElseThrow().getCondition();
        assertInstanceOf(OperatorCondition.class, c);

        OperatorCondition oc = (OperatorCondition) c;

        assertEquals(2, oc.getConditions().length);
        assertEquals(LogicalOperation.AND, oc.getOperation());

        assertEquals(BooleanCondition.TRUE, oc.getConditions()[0]);
        assertEquals(BooleanCondition.FALSE, oc.getConditions()[1]);
    }

}
