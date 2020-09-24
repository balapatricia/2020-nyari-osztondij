package problems.statespace;

abstract public class Operator {

    public abstract boolean applicable(State st) throws OperatorNotAllowedException;

    public abstract State apply(State st) throws OperatorNotAllowedException;
}
