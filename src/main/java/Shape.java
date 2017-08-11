import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

enum Shape {
    ROCK,
    PAPER,
    SCISSORS;

    Set<Shape> weakerShapes = new HashSet<>();

    public void setWeakerShapes(Shape... shapes) {
        weakerShapes.addAll(Arrays.asList(shapes));
    }

    public boolean winsAgainst(Shape shape) {
        return weakerShapes.contains(shape);
    }
}
