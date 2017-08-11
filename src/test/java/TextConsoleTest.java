import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class TextConsoleTest {

    @Test
    public void askNextShape_ShouldRepeatUntilAValidShape() throws Exception {
       Console console = new TextConsole(new Scanner("Ro\nkashk\nrashk\nrock\n"));
       final Shape shape = console.askNext();
        assertThat(shape, is(equalTo(Shape.ROCK)));
    }

    @Test
    public void askNextChape_shouldMatchExactWord() throws Exception {
        Console console = new TextConsole(new Scanner("asrocks\npaper\n"));
        final Shape shape = console.askNext();
        assertThat(shape, is(equalTo(Shape.PAPER)));
    }

    @Test
    public void GivenCorrectShape_askNextItShouldReturnThatShape() throws Exception {
        for (Shape shape : Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString()));
            assertThat(shape, is(equalTo(console.askNext())));
        }
    }

    @Test
    public void caseInsensitiveInputsAreAccepted() {
        for (Shape shape : Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString().toLowerCase()));
            assertThat(shape, is(equalTo(console.askNext())));
        }
    }
}
