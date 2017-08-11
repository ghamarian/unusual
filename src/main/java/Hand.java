import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Hand {
    private Set<Hand> winsAgainstSet;
    public static Hand instance;

    public Hand(Hand ...hands) {
        winsAgainstSet = new HashSet<>(Arrays.asList(hands));
    }

    public boolean winsAgainst(Hand hand) {
        return winsAgainstSet.contains(hand);
    }
}
