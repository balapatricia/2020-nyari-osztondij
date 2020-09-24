package games.game;

import games.statespace.Operator;
import games.statespace.State;

public class AlphabetaAlgorithm extends StepProposal {

    public AlphabetaAlgorithm(State state, int depth, int alpha,int beta) {
        this.state = state;
        this.depth = depth;
        this.numberOfStates = 1;
        if (depth == 0 || state.endState()){
            this.utility = state.miniMaxUtility();
        }
        else if (state.getPlayer() == 'A'){
            for (Operator op : State.getOperators()){
                if (alpha >= beta){
                    break;
                }
                if (op.applicable(state)){
                    State newState = op.apply(state);
                    AlphabetaAlgorithm alphabeta = new AlphabetaAlgorithm(newState,depth-1, alpha, beta);
                    if (alpha < alphabeta.utility){
                        alpha = alphabeta.utility;
                        this.proposalOp = op;
                    }
                    this.numberOfStates += alphabeta.numberOfStates;
                }
            }
            this.utility = (alpha < beta) ? alpha : beta;
        }
        else {
            for (Operator op : State.getOperators()){
                if (beta <= alpha){
                    break;
                }
                if (op.applicable(state)){
                    State newState = op.apply(state);
                    AlphabetaAlgorithm alphabeta = new AlphabetaAlgorithm(newState,depth-1, alpha, beta);
                    if (beta > alphabeta.utility){
                        beta = alphabeta.utility;
                        this.proposalOp = op;
                    }
                    this.numberOfStates += alphabeta.numberOfStates;
                }
            }
            this.utility = (beta > alpha) ? beta : alpha;
        }
//        System.out.println(this);
    }
}
