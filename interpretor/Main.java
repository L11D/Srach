public class Main {
    public static void main(String[] args) {
        StartLogicNode start = new StartLogicNode();
        BranchNode branch = new BranchNode();
        InputNode input1 = new InputNode();
        InputNode input2 = new InputNode();
        EqualNode equal = new EqualNode();
        PrintNode printTrue = new PrintNode();
        PrintNode printFalse = new PrintNode();
        NumberNode numberTrue = new NumberNode();
        NumberNode numberFalse = new NumberNode();

        start.setNext(branch);
        equal.setLeft(input1);
        equal.setRight(input2);
        branch.setNextTrue(printTrue);
        branch.setNextFalse(printFalse);
        branch.setEvaluateResult(equal);
        numberTrue.setValue(1);
        numberFalse.setValue(0);
        printTrue.setEvaluateResult(numberTrue);
        printFalse.setEvaluateResult(numberFalse);

        goLogic(start);
    }

    public static void goLogic(LogicNode node) {
        if (node.getNext() != null) {
            node.getNext().work();
            goLogic(node.getNext());
        }
        else
            System.out.print("End");
    }
}

