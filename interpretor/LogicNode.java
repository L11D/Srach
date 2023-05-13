public abstract class LogicNode implements LogicNodeWork {
    private LogicNode next;

    {
        this.next = null;
    }

    public LogicNode getNext() {
        return next;
    }

    public void setNext(LogicNode next) {
        this.next = next;
    }
}
