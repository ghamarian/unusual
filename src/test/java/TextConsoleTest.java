import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextConsoleTest {

    @Test
    public void askNextShape_ShouldRepeatUntilAValidShape() throws Exception {
       Console console = new TextConsole(new Scanner("Ro\nkashk\nrashk\nrock\n"));
       final RoundJudge.Shape shape = console.askNext();
        assertThat(shape, is(equalTo(RoundJudge.Shape.ROCK)));
    }

    @Test
    public void GivenCorrectShape_askNextItShouldReturnThatShape() throws Exception {
        for (RoundJudge.Shape shape : RoundJudge.Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString()));
            assertThat(shape, is(equalTo(console.askNext())));
        }
    }

    @Test
    public void caseInsensitiveInputsAreAccepted() {
        for (RoundJudge.Shape shape : RoundJudge.Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString().toLowerCase()));
            assertThat(shape, is(equalTo(console.askNext())));
        }
    }
}
