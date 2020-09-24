package games.statespace;

import java.util.HashSet;
import java.util.Set;

public abstract class State {

    protected char player;

    public abstract boolean endState();

    public abstract boolean isAWon();

    public abstract boolean isBWon();

    public abstract int miniMaxUtility();

//    public abstract int negaMaxUtility();

    public void changePlayer(){
        if (player == 'A') {
            player = 'B';
        }
        else {
            player = 'A';
        }
    }

    public abstract Operator readOperator();

    public boolean applicable(Operator op){
        return op.applicable(this);
    }

    public State apply(Operator op){
        return op.apply(this);
    }

    protected static Set<Operator> operators = new HashSet<>();

    public static Set<Operator> getOperators() {
        return operators;
    }

    public char getPlayer() {
        return player;
    }
}
