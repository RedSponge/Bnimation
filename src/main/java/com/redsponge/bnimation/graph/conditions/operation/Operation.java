package com.redsponge.bnimation.graph.conditions.operation;

import com.redsponge.bnimation.graph.conditions.Condition;

public interface Operation {

    boolean operate(Condition... conditions);

}
