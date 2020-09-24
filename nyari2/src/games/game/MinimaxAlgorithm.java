package games.game;

import games.statespace.Operator;
import games.statespace.State;

public class MinimaxAlgorithm extends StepProposal {

    public MinimaxAlgorithm(State state, int depth) {
        this.state = state;
        this.depth = depth;
        this.numberOfStates = 1;
        if (depth == 0 || state.endState()){
            this.utility = state.miniMaxUtility();
        }
        else if (state.getPlayer() == 'A'){
            this.utility = Integer.MIN_VALUE;
            for (Operator op : State.getOperators()){
                if (op.applicable(state)){
                    State newState = op.apply(state);
                    MinimaxAlgorithm minimax = new MinimaxAlgorithm(newState,depth-1);
                    if (this.utility < minimax.utility){
                        this.utility = minimax.utility;
                        this.proposalOp = op;
                    }
                    this.numberOfStates += minimax.numberOfStates;
                }
            }
        }
        else {
            this.utility = Integer.MAX_VALUE;
            for (Operator op : State.getOperators()){
                if (op.applicable(state)){
                    State newState = op.apply(state);
                    MinimaxAlgorithm minimax = new MinimaxAlgorithm(newState,depth-1);
                    if (this.utility > minimax.utility){
                        this.utility = minimax.utility;
                        this.proposalOp = op;
                    }
                    this.numberOfStates += minimax.numberOfStates;
                }
            }
        }
        System.out.println(this);
    }
}
