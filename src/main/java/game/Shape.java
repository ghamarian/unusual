package game;

import java.util.*;
import java.util.stream.Collectors;

public enum Shape {
    ROCK,
    PAPER,
    SCISSORS,
    QUIT;

    Set<Shape> weakerShapes = new HashSet<>();

    public void setWeakerShapes(Shape... shapes) {
        weakerShapes.addAll(Arrays.asList(shapes));
    }

    Winner winsAgainst(Shape shape) {
        if (this == shape){
           return Winner.DRAW;
        }
        return weakerShapes.contains(shape) ? Winner.USER : Winner.COMPUTER;
    }

    public static List<Shape> shapes() {
        return Arrays.stream(Shape.values()).filter(x -> x != QUIT).collect(Collectors.toList());
    }
}
