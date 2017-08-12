package strategy;

import game.Guesser;
import game.Shape;
import org.junit.Test;
import strategy.RandomGuesser;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNot.not;


public class RandomGuesserTest {
    @Test
    public void quitIsNotSelected() throws Exception {
        final Guesser randomGuesser = new RandomGuesser();
        for (int i = 0; i < 100; i++){
            assertThat(randomGuesser.nextGuess(), is(not(equalTo(Shape.QUIT))));
        }
    }
}
