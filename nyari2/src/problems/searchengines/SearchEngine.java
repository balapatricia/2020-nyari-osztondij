package searchengines;

import java.util.ArrayList;
import java.util.List;

abstract public class SearchEngine {

    protected boolean allSolution;
    protected boolean goalStateIsEnough;
    protected List<Node> terminalNodes = new ArrayList<>();

    abstract public void search();

    public List<Node> getTerminalNodes() {
        return terminalNodes;
    }

    public void printSolution(Node node){
        if (node != null){
            Node parent = node.parent;
            printSolution(parent);
            System.out.println(node);
        }
    }
}
