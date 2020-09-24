package problems.statespace;

public class OperatorNotAllowedException extends Exception {

    private Operator op;

    public OperatorNotAllowedException(Operator op) {
        this.op = op;
    }

    public Operator getOp() {
        return op;
    }

    @Override
    public String getMessage() {
        return String.format("%s [%s]", super.getMessage(), op.toString());
    }
}
