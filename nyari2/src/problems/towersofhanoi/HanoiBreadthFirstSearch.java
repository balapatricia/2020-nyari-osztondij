package problems.towersofhanoi;

import searchengines.Node;
import searchengines.modifiable.graph.breadthfirstsearch.BreadthFirstSearch;

public class HanoiBreadthFirstSearch {

    public static void main(String[] args) {

        HanoiState st = new HanoiState();
        BreadthFirstSearch bfs = new BreadthFirstSearch(st);
        bfs.search();
        if (bfs.getTerminalNodes().isEmpty()){
            System.out.println(":(");
        }
        else {
            System.out.println(":)");
            for (Node node : bfs.getTerminalNodes()) {
                System.out.println("Egy megoldás:");
                bfs.printSolution(node);
            }
        }
    }
}
