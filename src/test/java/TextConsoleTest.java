import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TextConsoleTest {

    @Test
    public void askNextHand_ShouldRepeatUntilAValidToken() throws Exception {
       Console console = new TextConsole(new Scanner("Ro\nkashk\nrashk\nrock\n"));
       final RoundJudge.Hand hand = console.askNext();
        assertThat(hand, is(equalTo(RoundJudge.Hand.ROCK)));
    }

    @Test
    public void GivenCorrectTokens_ItShouldReturnThatToken() throws Exception {
        for (RoundJudge.Hand hand : RoundJudge.Hand.values()) {
            Console console = new TextConsole(new Scanner(hand.toString()));
            assertThat(hand, is(equalTo(console.askNext())));
        }
    }

    @Test
    public void caseInsensitiveInputsAreAccepted() {
        for (RoundJudge.Hand hand : RoundJudge.Hand.values()) {
            Console console = new TextConsole(new Scanner(hand.toString().toLowerCase()));
            assertThat(hand, is(equalTo(console.askNext())));
        }
    }
}
