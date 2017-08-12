import java.util.*;

enum Shape {
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
        List<Shape> allShapes = new ArrayList<>(Arrays.asList(Shape.values()));
        allShapes.remove(Shape.QUIT);
        return Collections.unmodifiableList(allShapes);
    }
}
