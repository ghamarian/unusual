package game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ShapeTest {
    @Test
    public void shapes() {
        assertFalse(Shape.shapes().contains(Shape.QUIT));
    }
}