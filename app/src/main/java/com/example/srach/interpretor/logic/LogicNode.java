package com.example.srach.interpretor.logic;

import com.example.srach.interpretor.Node;

public abstract class LogicNode extends Node implements LogicNodeWork {
    private LogicNode next;
    public LogicNode() {
        next = null;
    }

    public LogicNode getNext() {
        return next;
    }

    public void setNext(LogicNode next) {
        this.next = next;
    }
}
