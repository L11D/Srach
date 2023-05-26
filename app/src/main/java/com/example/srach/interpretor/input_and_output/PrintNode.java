package com.example.srach.interpretor.input_and_output;


import com.example.srach.NewConsole;

import com.example.srach.interpretor.logic.LogicNode;
import com.example.srach.interpretor.math.MathNode;

public class PrintNode extends LogicNode {
    private MathNode evaluateResult;

    public void setConsole(NewConsole console) {
        this.console = console;
    }

    private NewConsole console;

    public PrintNode() {
        evaluateResult = null;
    }

    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        console.inputText(evaluateResult.evaluate().value);
        //System.out.println(evaluateResult.evaluate().value);
    }
}
