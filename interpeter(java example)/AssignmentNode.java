public class AssignmentNode extends LogicNode {
    private VariableNode variable;
    private MathNode evaluateResult;

    public void setName(VariableNode variable) {
        this.variable = variable;
    }

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    public void assignment() {
        variable.setValue(evaluateResult.evaluate());
    }

    @Override
    public void work() {
        assignment();
    }
}
