import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGuesser implements Guesser {

    private static final List<RoundJudge.Hand> hands = Collections.unmodifiableList(Arrays.asList(RoundJudge.Hand.values()));
    private static final Random random = new Random();

    @Override
    public RoundJudge.Hand nextGuess() {
        return hands.get(random.nextInt(hands.size()));
    }
}
