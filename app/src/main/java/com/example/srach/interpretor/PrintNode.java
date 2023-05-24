package com.example.srach.interpretor;

import android.widget.TextView;

public class PrintNode extends LogicNode {
    private MathNode evaluateResult;

    private TextView console;
    public void setConsole(TextView console){
        this.console = console;
    }
    public void setEvaluateResult(MathNode evaluateResult) {
        this.evaluateResult = evaluateResult;
    }

    @Override
    public void work() {
        //console.append(evaluateResult.evaluate().value);
        System.out.println(evaluateResult.evaluate().value);
    }
}
