package problems.towersofhanoi;

import problems.statespace.State;

import java.util.StringJoiner;

public class HanoiState extends State {

    static {
        for (char from = 'A'; from <= 'C'; from++){
            for (char to : new char[]{'A','B','C'}){
                for (int disk = 1; disk <= 3; disk++){
                    operators.add(new Move(from,to,disk));
                }
            }
        }
    }


    char[] h = new char[4];

    public HanoiState() {
        for (int i = 1; i<= 3; i++){
            h[i] = 'A';
        }
    }

    public HanoiState(HanoiState hs){
        for (int i = 1; i <=3; i++){
            h[i] = hs.h[i];
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof HanoiState)){
            return false;
        }
        for (int i = 1; i <= 3; i++){
            if (h[i] != ((HanoiState)obj).h[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(",","(",")");
        for (int i = 1; i <= 3; i++){
            sj.add(String.valueOf(h[i]));
        }
        return sj.toString();
    }

    @Override
    public boolean goal() {
        return h[1] == 'C' && h[2] == 'C' && h[3] == 'C';
    }
}
