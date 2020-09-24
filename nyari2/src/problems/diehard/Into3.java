package problems.diehard;

import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

public class Into3 extends Operator {

    @Override
    public boolean applicable(State st) throws OperatorNotAllowedException {
        if ((st instanceof DieHardState)){
            return ((DieHardState)st).h[1] < 3;
        }
        throw new OperatorNotAllowedException(this);
    }

    @Override
    public State apply(State st) throws OperatorNotAllowedException{
        if ((st instanceof DieHardState)){
            DieHardState hs = new DieHardState((DieHardState)st);
            hs.h[1] = 3;
            return hs;
        }
        throw new OperatorNotAllowedException(this);
    }
}
