import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RoundJudge {
//    enum Hand {
//        ROCK {
//            Set<Hand> winsAgainstSet = new HashSet<>();
//
//
//        },
//        PAPER,
//        SCISSOR;
//
//        Set<Hand> winsAgainstSet = new HashSet<>();
//
//        public boolean winsAgainst(Hand other) {
//            return winsAgainstSet.contains(other);
//        }
//    }

//    private static final int size = Hand.values().length;

//    private int[][] rules = new int[size][size];


    int judge(Hand first, Hand second) {
        if (first == second) {
            return 0;
        }
        if (first.winsAgainst(second)) {
            return 1;
        }
        else return -1;
    }
}
