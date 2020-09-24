package games.game;

import games.statespace.Operator;
import games.statespace.State;

public class Game {

    public static final int PLAYING_AGAINST_HUMAN = 1;
    public static final int MACHINE_START = 2;
    public static final int WORKING_WITH_MINIMAX = 4;
    public static final int WORKING_WITH_ALPHABETA = 8;
    public static final int THERE_IS_STEP_PROPOSAL = 16;

    protected static final int DEPTH = 5;

    protected State start;

    protected boolean isPlayingAgainstHuman;

    protected boolean isMachineStart;

    protected boolean isWorkingWithMinimax;

    protected boolean isWorkingWithAlphabeta;

    protected boolean isThereStepProposal;

    protected int depthForMachine;

    protected int depthForHuman;

    public Game(State state, int attributes, int depthForMachine, int depthForHuman) {
        this.start = state;
        this.isPlayingAgainstHuman = (attributes & 1) != 0;
        this.isMachineStart = (attributes & 2) != 0;
        this.isWorkingWithMinimax = (attributes & 4) != 0;
        this.isWorkingWithAlphabeta = (attributes & 8) != 0;
        this.isThereStepProposal = (attributes & 16) != 0;
        this.depthForMachine = depthForMachine;
        this.depthForHuman = depthForHuman;
    }

    public Game(State state, int attributes) {
        this(state,attributes,DEPTH,DEPTH);
    }

    public Game(State state) {
        this(state,WORKING_WITH_ALPHABETA|THERE_IS_STEP_PROPOSAL);
    }

    public void play(){
        State state = start;
        StepProposal sp = null;
        while (true){
            System.out.println("Current state: " + state);
            if (state.endState()){
                break;
            }
            Operator op = null;
            if (!isPlayingAgainstHuman && (isMachineStart && state.getPlayer() == 'A' || !isMachineStart && state.getPlayer() == 'B' )){
                if (isWorkingWithMinimax && !isWorkingWithAlphabeta){
                    sp = new MinimaxAlgorithm(state,depthForMachine);
                }
                if (isWorkingWithAlphabeta && !isWorkingWithMinimax){
                    sp = new AlphabetaAlgorithm(state,depthForMachine,Integer.MIN_VALUE, Integer.MAX_VALUE);
                }
                op = sp.proposalOp;
                System.out.printf("Machine's step: %s%n",op);
            }
            else {
                if (isThereStepProposal){
                    if (isWorkingWithMinimax && !isWorkingWithAlphabeta){
                        sp = new MinimaxAlgorithm(state,depthForMachine);
                    }
                    if (isWorkingWithAlphabeta && !isWorkingWithMinimax) {
                        sp = new AlphabetaAlgorithm(state, depthForMachine, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    }
                    op = sp.proposalOp;
                    System.out.printf("The proposal is: %s%n",op);
                }
                op = state.readOperator();
            }
            state = state.apply(op);
        }
        System.out.println("Game over!");
        if (state.isAWon()){
            System.out.println("The winner is Player A!");
        }
        else if (state.isBWon()){
            System.out.println("The winner is Player B!");
        }
        else {
            System.out.println("The game is tied!");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("isPlayingAgainstHuman: ").append(isPlayingAgainstHuman).append(System.lineSeparator());
        sb.append("isMachineStart: ").append(isMachineStart).append(System.lineSeparator());
        sb.append("isWorkingWithMinimax: ").append(isWorkingWithMinimax).append(System.lineSeparator());
        sb.append("isWorkingWithAlphabeta: ").append(isWorkingWithAlphabeta).append(System.lineSeparator());
        sb.append("isThereStepProposal: ").append(isThereStepProposal).append(System.lineSeparator());
        return sb.toString();
    }
}
