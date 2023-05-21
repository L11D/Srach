package com.example.srach.interpretor;

public abstract class LogicNode extends Node implements LogicNodeWork {
    private LogicNode next;
    LogicNode() {
        next = null;
    }

    public LogicNode getNext() {
        return next;
    }

    public void setNext(LogicNode next) {
        this.next = next;
    }
}
