import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RoundJudge {
    public static RoundJudge instance;

    enum Hand {
        ROCK,
        PAPER,
        SCISSORS;

        Set<Hand> winsAgainstSet = new HashSet<>();
        public void setup(Hand... hands) {
            winsAgainstSet.addAll(Arrays.asList(hands));
        }

        public boolean winsAgainst(Hand hand) {
            return winsAgainstSet.contains(hand);
        }
    }

    private RoundJudge(){
        Hand.ROCK.setup(Hand.SCISSORS);
        Hand.PAPER.setup(Hand.ROCK);
        Hand.SCISSORS.setup(Hand.PAPER);
    }

    public static RoundJudge getInstance() {
        if (instance == null) {
            instance = new RoundJudge();
        }
        return instance;
    }

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
