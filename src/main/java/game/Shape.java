package game;

import java.util.*;
import java.util.stream.Collectors;

public enum Shape {
    ROCK,
    PAPER,
    SCISSORS,
    QUIT;

    private Set<Shape> looserShapes = new HashSet<>();

    void setLooserShapes(Shape... shapes) {
        looserShapes.addAll(Arrays.asList(shapes));
    }

    boolean winsAgainst(Shape shape) {
        return looserShapes.contains(shape);
    }

    public static List<Shape> shapes() {
        return Arrays.stream(Shape.values()).filter(x -> x != QUIT).collect(Collectors.toList());
    }

}
