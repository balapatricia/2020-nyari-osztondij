package problems.towersofhanoi;

import searchengines.Node;
import searchengines.modifiable.backtracking.BacktrackSearch;

public class HanoiBacktrackSearch {

    public static void main(String[] args) {

        HanoiState st = new HanoiState();
        BacktrackSearch bs = new BacktrackSearch(st);
        bs.search();
        if (bs.getTerminalNodes().isEmpty()){
            System.out.println(":(");
        }
        else {
            System.out.println(":)");
            for (Node node : bs.getTerminalNodes()) {
                System.out.println("Egy megoldás:");
                bs.printSolution(node);
            }
        }
    }
}
