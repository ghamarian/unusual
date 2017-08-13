package game;

import gameInterface.TextConsole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

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

    private TextConsole generateConsoleGenerating(Shape... shapes) {
        final String newline = "\n";
        final String prefix = String.valueOf(shapes.length) + newline;
        String input = Arrays.stream(shapes).map(Object::toString).collect(Collectors.joining(newline, prefix, newline));
        return new TextConsole(new Scanner(input));
    }

    @Test
    public void threeWins() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.PAPER).thenReturn(Shape.PAPER).thenReturn(Shape.PAPER);
        GameEngine game = new GameEngine(generateConsoleGenerating(Shape.ROCK, Shape.ROCK, Shape.ROCK), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 0, 3, 0);
    }

    @Test
    public void threeLosses() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(generateConsoleGenerating(Shape.ROCK, Shape.ROCK, Shape.ROCK), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 3, 0, 0);
    }

    @Test
    public void threeDraws() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(generateConsoleGenerating(Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 0, 0, 3);
    }

    @Test
    public void threeMixed() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(generateConsoleGenerating(Shape.SCISSORS, Shape.ROCK, Shape.PAPER), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 1, 1, 1);
    }

    @Test
    public void testQuit() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(generateConsoleGenerating(Shape.SCISSORS, Shape.ROCK, Shape.QUIT), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 1, 0, 1);
        assertThat(scoreboard.numberOfRounds(), is(equalTo(2L)));
    }


    private void assertScoreBoard(Scoreboard scoreboard, long user, long computer, long draws) {
        assertThat(scoreboard.getUserScore(), is(equalTo(user)));
        assertThat(scoreboard.getComputerScore(), is(equalTo(computer)));
        assertThat(scoreboard.getDrawScore(), is(equalTo(draws)));
    }
}
