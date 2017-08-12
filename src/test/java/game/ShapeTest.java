package game;

import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class ShapeTest {
    @Test
    public void shapes() throws Exception {
        assertFalse(Shape.shapes().contains(Shape.QUIT));
    }
}