package game;

import gameInterface.TextConsole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameEngineTest {

    @Mock
    private Guesser guesser;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    private TextConsole createConsoleGenerating(Shape... shapes) {
        final String newline = "\n";
        final String prefix = String.valueOf(shapes.length) + newline;
        String input = Arrays.stream(shapes).map(Object::toString).collect(Collectors.joining(newline, prefix, newline));
        return new TextConsole(new Scanner(input));
    }

    private void mockGuesserWith(Shape... shapes) {
        OngoingStubbing<Shape> when = when(guesser.nextGuess());
        for (Shape shape : shapes) {
            when = when.thenReturn(shape);
        }
    }

    private Scoreboard runGame(Shape[] computerGuesses, Shape[] userInputs) {
        mockGuesserWith(computerGuesses);
        GameEngine game = new GameEngine(createConsoleGenerating(userInputs), guesser);
        return game.play();
    }

    @Test
    public void threeWins() throws Exception {
        Scoreboard scoreboard = runGame(new Shape[]{Shape.PAPER, Shape.PAPER, Shape.PAPER}, new Shape[]{Shape.ROCK, Shape.ROCK, Shape.ROCK});
        assertScoreBoard(scoreboard, 0, 3, 0);
    }

    @Test
    public void threeLosses() throws Exception {
        Scoreboard scoreboard = runGame(new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS}, new Shape[]{Shape.ROCK, Shape.ROCK, Shape.ROCK});
        assertScoreBoard(scoreboard, 3, 0, 0);
    }

    @Test
    public void threeDraws() throws Exception {
        Scoreboard scoreboard = runGame(new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS}, new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS});
        assertScoreBoard(scoreboard, 0, 0, 3);
    }

    @Test
    public void threeMixed() throws Exception {
        Scoreboard scoreboard = runGame(new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS}, new Shape[]{Shape.SCISSORS, Shape.ROCK, Shape.PAPER});
        assertScoreBoard(scoreboard, 1, 1, 1);
    }

    @Test
    public void testQuit() throws Exception {
        Scoreboard scoreboard = runGame(new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS}, new Shape[]{Shape.SCISSORS, Shape.ROCK, Shape.QUIT});
        assertThat(scoreboard.numberOfRounds(), is(equalTo(2L)));
    }


    private void assertScoreBoard(Scoreboard scoreboard, long user, long computer, long draws) {
        assertThat(scoreboard.getUserScore(), is(equalTo(user)));
        assertThat(scoreboard.getComputerScore(), is(equalTo(computer)));
        assertThat(scoreboard.getDrawScore(), is(equalTo(draws)));
    }
}
