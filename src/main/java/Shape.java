import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

enum Shape {
    ROCK,
    PAPER,
    SCISSORS;

    Set<Shape> winsAgainstSet = new HashSet<>();

    public void setup(Shape... shapes) {
        winsAgainstSet.addAll(Arrays.asList(shapes));
    }

    public boolean winsAgainst(Shape shape) {
        return winsAgainstSet.contains(shape);
    }
}
