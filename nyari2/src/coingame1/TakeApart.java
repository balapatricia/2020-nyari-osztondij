package coingame1;

import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

import java.util.List;

public class TakeApart extends Operator {

    int from;

    int remain;

    public TakeApart(int from, int remain) {
        this.from = from-1;
        this.remain = remain;
    }

    public int getFrom() {
        return from;
    }

    public int getRemain() {
        return remain;
    }

    @Override
    public String toString() {
        return "TakeApart{" +
                "from=" + from +
                ", remain=" + remain +
                '}';
    }


    @Override
    public boolean applicable(State st) throws OperatorNotAllowedException {
        if (st instanceof CoinGameState){
            CoinGameState cgs = (CoinGameState) st;
            if (from < 0 || cgs.h.size()-1 < from){
                return false;
            }
            if (remain < 1 || Math.floor(cgs.h.get(from)/2) < remain){
                return false;
            }
            if (cgs.h.get(from) <= 2){
                return false;
            }
            return true;
        }
        throw new OperatorNotAllowedException(this);
    }

    @Override
    public CoinGameState apply(State st) throws OperatorNotAllowedException {
        if (st instanceof CoinGameState){
            CoinGameState cgs = (CoinGameState)st;
            List<Integer> newh = cgs.h;
            char newplayer;
            int coins = newh.get(from);
            newh.remove(from);
            newh.add(from, remain);
            newh.add(coins-remain);
            if (cgs.player == 'A'){
                newplayer = 'B';
            }
            else{
                newplayer = 'A';
            }
            return new CoinGameState(newh, newplayer);
        }
        throw new OperatorNotAllowedException(this);
    }
}
