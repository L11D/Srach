public class PrintNode extends LogicNode {
    private MathNodeInt evaluateResult;

    public void setEvaluateResult(MathNodeInt evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        System.out.println(evaluateResult.evaluate());
    }
}
