package searchengines.modifiable.backtracking;

import searchengines.Node;
import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

import java.util.HashSet;
import java.util.Set;

public class BacktrackNode extends Node {

    protected Set<Operator> applicableButNotUsedOperators = new HashSet<>();

    private void setUp(){
        for (Operator item : State.getOperators()){
            try {
                if (state.applicable(item)){
                    applicableButNotUsedOperators.add(item);
                }
            }
            catch (OperatorNotAllowedException onae){
            }
        }
    }

    public BacktrackNode(State state) {
        super(state);
        setUp();
    }

    public BacktrackNode(Node parent, Operator operator) throws OperatorNotAllowedException {
        super(parent, operator);
        setUp();
    }

    public Set<Operator> getApplicableButNotUsedOperators() {
        return applicableButNotUsedOperators;
    }
}
