package com.example.srach.interpretor.operators;

import com.example.srach.interpretor.math.MathNode;

public abstract class OperatorNode extends MathNode {
    private MathNode left;
    private MathNode right;
    public OperatorNode() {
        left = null;
        right = null;
    }

    public MathNode getLeft() {
        return left;
    }

    public void setLeft(MathNode left) {
        this.left = left;
    }

    public MathNode getRight() {
        return right;
    }

    public void setRight(MathNode right) {
        this.right = right;
    }
}
