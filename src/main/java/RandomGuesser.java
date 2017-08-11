import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//strategy pattern
public class RandomGuesser implements Guesser {

    private static final List<Shape> SHAPES = Collections.unmodifiableList(Arrays.asList(Shape.values()));
    private static final Random random = new Random();

    @Override
    public Shape nextGuess() {
        return SHAPES.get(random.nextInt(SHAPES.size()));
    }
}
