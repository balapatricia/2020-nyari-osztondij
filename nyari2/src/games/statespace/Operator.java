package games.statespace;

abstract public class Operator {

    public abstract boolean applicable(State st);

    public abstract State apply(State st);
}
