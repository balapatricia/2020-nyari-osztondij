package searchengines.modifiable.backtracking;

import searchengines.SearchEngine;
import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

import java.util.Set;
import java.util.Stack;

public class BacktrackSearch extends SearchEngine {

    protected Stack<BacktrackNode> stack = new Stack<>();

    public BacktrackSearch(State state) {
        stack.push(new BacktrackNode(state));
    }

    @Override
    public void search() {
        while (true){
            if (stack.empty()){
                break;
            }
            BacktrackNode currentNode = stack.peek();
            System.out.println(currentNode);
            if (currentNode.getState().goal()){
                terminalNodes.add(currentNode);
                break;
            }
            Set<Operator> applicableButNotUsedOperators = currentNode.getApplicableButNotUsedOperators();
            if (applicableButNotUsedOperators.isEmpty()){
                stack.pop();
                continue;
            }
            Operator op = applicableButNotUsedOperators.iterator().next();
            try {
                BacktrackNode newNode = new BacktrackNode(currentNode,op);
                if (!stack.contains(newNode)){
                    stack.push(newNode);
                }
            }
            catch (OperatorNotAllowedException onae){}
            applicableButNotUsedOperators.remove(op);
        }
    }
}
