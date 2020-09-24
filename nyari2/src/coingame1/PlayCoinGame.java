package coingame1;

import problems.statespace.OperatorNotAllowedException;

import java.util.Scanner;
import java.util.Stack;

public class PlayCoinGame {

    protected static Stack<CoinGameState> stack = new Stack<>();

    public static void main(String[] args) {
        System.out.println("A lépését a következő formátumban adja meg: hanyadik oszlopot szeretné szétszedni, hány érme maradjon a helyén");
        Scanner sc = new Scanner(System.in);
        stack.push(new CoinGameState());
        while (true){
            CoinGameState currentState = stack.peek();
            if (currentState.goal()){
                System.out.println(String.format("A végső állapot %s",currentState.h.toString()));
                if (currentState.player == 'A'){
                    System.out.println("Gratulálok a B játékosnak, nyertél!");
                }
                else {
                    System.out.println("Gratulálok az A játékosnak, nyertél!");
                }
                break;
            }
            System.out.println(currentState.toString());
            if (currentState.player == 'A') {
                try {
                    System.out.println(currentState.toString());
                    TakeApart ta = (TakeApart)CoinMiniMax.miniMaxDecision(new MiniMaxNode(currentState));
                    System.out.println(currentState.toString());
                    stack.push(ta.apply(currentState));
                } catch (OperatorNotAllowedException e) {}
            }
            else {
                String[] move = sc.nextLine().split(", ");
                TakeApart ta = new TakeApart(Integer.parseInt(move[0]), Integer.parseInt(move[1]));
                try {
                    if (ta.applicable(currentState)) {
                        stack.push(ta.apply(currentState));
                    } else {
                        System.out.println("Nem szabályos lépés.");
                    }
                } catch (OperatorNotAllowedException onae) { }
            }
        }
    }
}
