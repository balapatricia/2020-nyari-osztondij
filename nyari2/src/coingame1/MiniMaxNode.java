package coingame1;

import searchengines.Node;
import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

import java.util.HashSet;
import java.util.Set;

public class MiniMaxNode extends Node {

    protected Set<Operator> applicableButNotUsedOperators = new HashSet<>();

    private void setUp(){
        for (Operator item : State.getOperators()){
            try {
                if (item.applicable(state)){
                    applicableButNotUsedOperators.add(item);
                }
            }
            catch (OperatorNotAllowedException onae){
            }
        }
    }

    public MiniMaxNode(State state) {
        super(state);
        setUp();
    }

    public MiniMaxNode(Node parent, Operator operator) throws OperatorNotAllowedException {
        super(parent, operator);
        setUp();
    }

    public Set<Operator> getApplicableButNotUsedOperators() {
        return applicableButNotUsedOperators;
    }

    public int getUtility(){
        CoinGameState cgs = (CoinGameState)state;
        int canMove = 0;
        if (cgs.player == 'B' && cgs.goal()){
            return 10;
        }
        if (cgs.player == 'A' && cgs.goal()){
            return -10;
        }
        return 0;
    }
}
