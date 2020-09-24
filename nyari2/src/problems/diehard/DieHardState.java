package problems.diehard;

import problems.statespace.State;

public class DieHardState extends State {

    static {
        operators.add(new Into3());
        operators.add(new Into5());
        operators.add(new Out3());
        operators.add(new Out5());
        operators.add(new Transfuse35());
        operators.add(new Transfuse53());
    }

    int[] h = new int[3];

    public DieHardState() {
        h[1] = 0;
        h[2] = 0;
    }

    public DieHardState(DieHardState hs) {
        for (int i = 1; i <=2; i++){
            h[i] = hs.h[i];
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DieHardState)){
            return false;
        }
        for (int i = 1; i <= 2; i++){
            if (h[i] != ((DieHardState)obj).h[i]){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("(%i,%i)",h[1],h[2]);
    }

    @Override
    public boolean goal() {
        return h[2] == 4;
    }

}
