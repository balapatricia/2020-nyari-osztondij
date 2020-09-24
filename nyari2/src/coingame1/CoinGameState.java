package coingame1;

import problems.statespace.State;

import java.util.ArrayList;
import java.util.List;

public class CoinGameState extends State {

    static {
        for (int from = 0; from <= 6; from++){
            for (int remain = 1; remain <= 6; remain++){
                operators.add(new TakeApart(from,remain));
            }
        }
    }

    List<Integer> h = new ArrayList<>();
    char player;

    public CoinGameState() {
        h.clear();
        h.add(7);
        player = 'A';
    }

    public CoinGameState(List<Integer> h, char player) {
        this.h = h;
        this.player = player;
    }

    @Override
    public String toString() {
        return String.format("A(z) %c játékos következik. %nA jelenlegi állapot: %s",player,h.toString());
    }

    @Override
    public boolean goal() {
        for (int item : h){
            if (item > 2){
                return false;
            }
        }
        return true;
    }
}
