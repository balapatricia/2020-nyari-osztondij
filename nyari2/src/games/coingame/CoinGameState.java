package games.coingame;

import games.statespace.Operator;
import games.statespace.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class CoinGameState extends State {

    static {
        for (int i = 1; i < 7; i++){
            for (int j = 1; j < 4; j++){
                operators.add(new TakeApart(i,j));
            }
        }

    }

    protected List<Integer> h = new ArrayList<>();

    public CoinGameState() {
        h.add(7);
        player = 'A';
    }

    public CoinGameState(CoinGameState cgs) {
        h = new ArrayList<>(cgs.getH());
        player = cgs.getPlayer();
    }

    @Override
    public boolean endState() {
        for (int item : h) {
            if (item > 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isAWon() {
        return endState() && player == 'B';
    }

    @Override
    public boolean isBWon() {
        return endState() && player == 'A';
    }

    @Override
    public int miniMaxUtility() {
        if (isAWon()) {
            return 10;
        }
        if (isBWon()) {
            return -10;
        }
        int equals_3 = 0;
        int equals_4 = 0;
        int greater_4 = 0;
        for (int item : h) {
            if (item == 3) {
                equals_3++;
            } else if (item == 4) {
                equals_4++;
            } else if (item > 4) {
                greater_4++;
            }
        }
        if (equals_3 == 0 && equals_4 == 1 && greater_4 == 0) {
            return player == 'A' ? 8 : -8;
        }
        if (equals_3 % 2 == 0 && equals_4 == 0 && greater_4 == 0) {
            return player == 'A' ? -9 : 9;
        }
        if (equals_3 == 0 && equals_4 % 2 == 0 && greater_4 == 0) {
            return player == 'A' ? -7 : 7;
        }
        if ((equals_3 + equals_4) % 2 == 0  && greater_4 == 0) {
            return player == 'A' ? -5 : 5;
        }
        return 0;
    }

    @Override
    public Operator readOperator() {
        Scanner sc = new Scanner(System.in);
        Operator op = null;
        while (true){
            System.out.print("Type your step, please: ");
            int from = sc.nextInt();
            int remain = sc.nextInt();
            op = new TakeApart(from,remain);
            if (op.applicable(this)){
                break;
            }
            else {
                System.out.println("This operator is not applicable.");
            }
        }
        return op;
    }

    public List<Integer> getH() {
        return h;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.join(" ",h.stream().map(p -> String.valueOf(p)).collect(Collectors.toList())));
        sb.append(System.lineSeparator());
        sb.append("The next player is: Player ");
        sb.append(player);
        return sb.toString();
    }
}
