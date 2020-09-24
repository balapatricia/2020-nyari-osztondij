package searchengines;
import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

public class Node {

    protected State state;
    protected Node parent;
    protected Operator operator;
    protected int depth;

    public Node(State state) {
        this.state = state;
        parent = null;
        operator = null;
        depth = 0;

    }

    public Node(Node parent, Operator operator) throws OperatorNotAllowedException {
        this.parent = parent;
        this.operator = operator;
        state = parent.state.apply(operator);
        depth = parent.depth + 1;
    }

    public State getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public Operator getOperator() {
        return operator;
    }

    public int getDepth() {
        return depth;
    }

    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                ", operator=" + operator +
                ", depth=" + depth +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Node)){
            return false;
        }
        return state.equals(((Node) obj).state);
    }
}
