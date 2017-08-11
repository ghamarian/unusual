import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RandomGuesser implements Guesser {

    private static final List<RoundJudge.Shape> SHAPES = Collections.unmodifiableList(Arrays.asList(RoundJudge.Shape.values()));
    private static final Random random = new Random();

    @Override
    public RoundJudge.Shape nextGuess() {
        return SHAPES.get(random.nextInt(SHAPES.size()));
    }
}
