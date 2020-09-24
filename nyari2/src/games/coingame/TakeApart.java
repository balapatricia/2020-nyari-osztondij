package games.coingame;

import games.statespace.Operator;
import games.statespace.State;

public class TakeApart extends Operator {

    private int from;

    private int remain;

    public TakeApart(int from, int remain) {
        this.from = from;
        this.remain = remain;
    }

    @Override
    public boolean applicable(State st){
        if (!(st instanceof CoinGameState)){
            return false;
        }
        CoinGameState cgs = (CoinGameState)st;
        if (from > cgs.getH().size() || from < 1){
            return false;
        }
        if (remain < 1 || remain >= cgs.getH().get(from-1)){
            return false;
        }
        if (cgs.getH().get(from-1) <= 2){
            return false;
        }
        return true;
    }

    @Override
    public State apply(State st){
        if (!(st instanceof CoinGameState)){
            return null;
        }
        CoinGameState cgs = new CoinGameState((CoinGameState)st);
        int coins = cgs.getH().get(from-1);
        cgs.getH().remove(from-1);
        cgs.getH().add(from-1, remain);
        cgs.getH().add(coins-remain);
        cgs.changePlayer();
        return cgs;
    }

    @Override
    public String toString() {
        return "TakeApart{" +
                "from=" + from +
                ", remain=" + remain +
                '}';
    }

    public int getFrom() {
        return from;
    }

    public int getRemain() {
        return remain;
    }
}
