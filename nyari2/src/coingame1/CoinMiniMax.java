package coingame1;

import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;

public class CoinMiniMax {

    static public Operator miniMaxDecision(MiniMaxNode miniMaxNode) throws OperatorNotAllowedException {
        int v = maxValue(miniMaxNode);
        for (Operator op : miniMaxNode.applicableButNotUsedOperators){
            MiniMaxNode newMiniMaxNode = new MiniMaxNode(miniMaxNode,op);
            if (newMiniMaxNode.getUtility() == v){
                return op;
            }
        }
        return null;
    }

    static public int maxValue(MiniMaxNode miniMaxNode) throws OperatorNotAllowedException {
        if (miniMaxNode.getState().goal()){
            return miniMaxNode.getUtility();
        }
        int v = Integer.MIN_VALUE;
        for (Operator op : miniMaxNode.applicableButNotUsedOperators) {
            MiniMaxNode newMiniMaxNode = new MiniMaxNode(miniMaxNode,op);
                v = Integer.max(v, minValue(newMiniMaxNode));

            }
        return v;
    }

    static public int minValue(MiniMaxNode miniMaxNode) throws OperatorNotAllowedException {
        if (miniMaxNode.getState().goal()){
            return miniMaxNode.getUtility();
        }
        int v = Integer.MAX_VALUE;
        for (Operator op : miniMaxNode.applicableButNotUsedOperators) {
            MiniMaxNode newMiniMaxNode = new MiniMaxNode(miniMaxNode,op);
                v = Integer.min(v,maxValue(newMiniMaxNode));
            }
        return v;
    }
}
