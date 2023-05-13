public abstract class OperatorNode extends Node {
    private MathNodeInt left;
    private MathNodeInt right;

    {
        this.left = null;
        this.right = null;
    }

    public void setLeft(MathNodeInt left) {
        this.left = left;
    }

    public void setRight(MathNodeInt right) {
        this.right = right;
    }

    public MathNodeInt getLeft() {
        return left;
    }

    public MathNodeInt getRight() {
        return right;
    }
}
