package problems.diehard;

import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

public class Into5 extends Operator {


    @Override
    public boolean applicable(State st) throws OperatorNotAllowedException {
        if ((st instanceof DieHardState)){
            return ((DieHardState)st).h[2] < 5;
        }
        throw new OperatorNotAllowedException(this);
    }

    @Override
    public State apply(State st) throws OperatorNotAllowedException {
        if ((st instanceof DieHardState)){
            DieHardState hs = new DieHardState((DieHardState)st);
            hs.h[2] = 5;
            return hs;
        }
        throw new OperatorNotAllowedException(this);
    }
}
