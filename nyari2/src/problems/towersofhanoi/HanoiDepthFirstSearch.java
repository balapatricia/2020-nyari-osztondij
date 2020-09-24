package problems.towersofhanoi;

import searchengines.modifiable.graph.depthfirstsearch.DepthFirstSearch;
import searchengines.Node;

public class HanoiDepthFirstSearch {

    public static void main(String[] args) {

        HanoiState st = new HanoiState();
        DepthFirstSearch dfs = new DepthFirstSearch(st);
        dfs.search();
        if (dfs.getTerminalNodes().isEmpty()){
            System.out.println(":(");
        }
        else {
            System.out.println(":)");
            for (Node node : dfs.getTerminalNodes()) {
                System.out.println("Egy megoldás:");
                dfs.printSolution(node);
            }
        }
    }

}
