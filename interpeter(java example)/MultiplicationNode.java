public class MultiplicationNode extends OperatorNode {
    private MathNode left;
    private MathNode right;

    {
        this.left = null;
        this.right = null;
    }

    public void setLeft(MathNode left) {
        this.left = left;
    }

    public void setRight(MathNode right) {
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate() * right.evaluate();
    }
}
