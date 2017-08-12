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

    public Score winsAgainst(Shape shape) {
        if (this == shape){
           return Score.DRAW;
        }
        return weakerShapes.contains(shape) ? Score.WON : Score.LOST;
    }

    public static List<Shape> shapes() {
        return Arrays.stream(Shape.values()).filter(x -> x != QUIT).collect(Collectors.toList());
    }
}
