package com.example.srach.interpritator;

public class EqualNode extends OperatorNodeBool {
    @Override
    public boolean evaluate() {
        return super.getLeft().evaluate() == super.getRight().evaluate();
    }
}
