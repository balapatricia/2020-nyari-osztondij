package problems.statespace;

import java.util.HashSet;
import java.util.Set;

abstract public class State {

    abstract public boolean goal();

    public boolean applicable(Operator op) throws OperatorNotAllowedException{
        return op.applicable(this);
    }

    public State apply(Operator op) throws OperatorNotAllowedException{
        return op.apply(this);
    }

    protected static Set<Operator> operators = new HashSet<>();

    public static Set<Operator> getOperators() {
        return operators;
    }
}
