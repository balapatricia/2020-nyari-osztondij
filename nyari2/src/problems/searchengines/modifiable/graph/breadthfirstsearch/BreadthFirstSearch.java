package searchengines.modifiable.graph.breadthfirstsearch;

import searchengines.Node;
import searchengines.modifiable.graph.GraphSearch;
import problems.statespace.Operator;
import problems.statespace.OperatorNotAllowedException;
import problems.statespace.State;

public class BreadthFirstSearch extends GraphSearch {

    public BreadthFirstSearch(State state) {
        open.add(new Node(state));
    }

    @Override
    public void search() {
       while (true){
           printDataBase();
           if (open.isEmpty()){
               break;
           }
           Node currentNode = open.getFirst();
           if (currentNode.getState().goal()){
               terminalNodes.add(currentNode);
               break;
           }
           closed.add(open.removeFirst());
           expands(currentNode);
       }
    }

    @Override
    protected void expands(Node node) {
        for (Operator item : State.getOperators()){
            try {
                if (node.getState().applicable(item)){
                    Node newNode = new Node(node,item);
                    if (!open.contains(newNode)&& !closed.contains(newNode)){
                        open.addLast(newNode);
                    }
                }
            }
            catch (OperatorNotAllowedException onae){
            }
        }
    }
}
