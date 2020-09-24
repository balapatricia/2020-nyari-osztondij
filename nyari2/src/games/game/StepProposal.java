package games.game;

import games.statespace.Operator;
import games.statespace.State;

public abstract class StepProposal {

    protected State state;

    protected int depth;

    protected Operator proposalOp;

    protected int utility;

    protected int numberOfStates;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current state: ").append(state).append(System.lineSeparator());
        sb.append("Depht: ").append(depth).append(System.lineSeparator());
        sb.append("Proposal operator: ").append(proposalOp).append(System.lineSeparator());
        sb.append("Utility: ").append(utility).append(System.lineSeparator());
        sb.append("Number of states: ").append(numberOfStates).append(System.lineSeparator());
        return sb.toString();
    }
}
