package problems.towersofhanoi;

import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

public class Move extends Operator {

    private char from;

    private char to;

    private int disk;

    public Move(char from, char to, int disk) {
        this.from = from;
        this.to = to;
        this.disk = disk;
    }

    public char getFrom() {
        return from;
    }

    public char getTo() {
        return to;
    }

    public int getDisk() {
        return disk;
    }

    @Override
    public String toString() {
        return "Move{" +
                "from=" + from +
                ", to=" + to +
                ", disk=" + disk +
                '}';
    }


    @Override
    public boolean applicable(State st) throws OperatorNotAllowedException {
        if (st instanceof HanoiState){
            HanoiState hs = (HanoiState)st;
            if (from != (hs.h[disk])){
                return false;
            }
            for (int i = 1; i <= 3; i++){
                if (hs.h[i] == from && i < disk){
                    return false;
                }
            }
            for (int i = 1; i <= 3; i++){
                if (hs.h[i] == to && i <= disk){
                    return false;
                }
            }
            return true;
        }
        throw new OperatorNotAllowedException(this);
    }

    @Override
    public State apply(State st) throws OperatorNotAllowedException {
        if (st instanceof HanoiState){
            HanoiState hs = new HanoiState((HanoiState)st);
            for (int i = 1; i <= 3; i++){
                if (i == disk){
                    hs.h[i] = to;
                }
            }
            return hs;
        }
        throw new OperatorNotAllowedException(this);
    }
}
