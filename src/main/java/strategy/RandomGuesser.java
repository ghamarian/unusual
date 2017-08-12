package strategy;

import game.Guesser;
import game.Shape;

import java.util.*;

public class RandomGuesser implements Guesser {

    private final List<Shape> SHAPES = Shape.shapes();
    private static final Random random = new Random();

    @Override
    public Shape nextGuess() {
        return SHAPES.get(random.nextInt(SHAPES.size()));
    }
}

