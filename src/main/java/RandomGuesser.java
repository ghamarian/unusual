import java.util.*;

//strategy pattern
public class RandomGuesser implements Guesser {

    private final List<Shape> SHAPES;
    private static final Random random = new Random();

    public RandomGuesser() {
        List<Shape> allShapes = new ArrayList<>(Arrays.asList(Shape.values()));
        allShapes.remove(Shape.QUIT);
        SHAPES = Collections.unmodifiableList(allShapes);
    }

    @Override
    public Shape nextGuess() {
        return SHAPES.get(random.nextInt(SHAPES.size()));
    }
}
