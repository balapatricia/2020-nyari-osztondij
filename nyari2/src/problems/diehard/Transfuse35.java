package problems.diehard;

import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

public class Transfuse35 extends Operator {


    @Override
    public boolean applicable(State st) throws OperatorNotAllowedException {
        if ((st instanceof DieHardState)){
            return ((DieHardState)st).h[1] > 0 && ((DieHardState)st).h[2] < 5;
        }
        throw new OperatorNotAllowedException(this);
    }

    @Override
    public State apply(State st) throws OperatorNotAllowedException {
        if ((st instanceof DieHardState)){
            DieHardState hs = new DieHardState();
            hs.h[1] = ((DieHardState)st).h[1] - Math.min(((DieHardState)st).h[1], 5-((DieHardState)st).h[2]);
            hs.h[2] = ((DieHardState)st).h[2] + Math.min(((DieHardState)st).h[1], 5-((DieHardState)st).h[2]);
            return hs;
        }
        throw new OperatorNotAllowedException(this);
    }
}
