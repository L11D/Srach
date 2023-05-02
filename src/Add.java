public class Add implements Node {
    private final Node left;
    private final Node right;

    public Add(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return left.evaluate() + right.evaluate();
    }
}
