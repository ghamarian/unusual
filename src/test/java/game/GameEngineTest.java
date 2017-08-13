package game;

import ui.TextConsole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameEngineTest {

    @Mock
    private Guesser guesser;

    private TextConsole console;
    private PrintStream out;
    private ByteArrayOutputStream outputResult;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        outputResult = new ByteArrayOutputStream();
        out = new PrintStream(outputResult);
    }

    private void createConsoleGenerating(Shape... shapes) {
        final String newline = "\n";
        final String prefix = String.valueOf(shapes.length) + newline;
        String input = Arrays.stream(shapes).map(Object::toString).collect(Collectors.joining(newline, prefix, newline));
        console = spy(new TextConsole(new Scanner(input), out));
    }

    private void mockGuesserWith(Shape... shapes) {
        OngoingStubbing<Shape> when = when(guesser.nextGuess());
        for (Shape shape : shapes) {
            when = when.thenReturn(shape);
        }
    }

    private GameEngine createGame(Shape[] userInputs, Shape[] computerGuesses) {
        createConsoleGenerating(userInputs);
        mockGuesserWith(computerGuesses);
        return new GameEngine(console, guesser);
    }

    @Test
    public void threeWins() {
        GameEngine game = createGame(new Shape[]{Shape.PAPER, Shape.PAPER, Shape.PAPER}, new Shape[]{Shape.ROCK, Shape.ROCK, Shape.ROCK});
        Scoreboard scoreboard = game.play();

        assertScoreBoard(scoreboard, 3, 0, 0);
        assertThat(outputResult.toString(), containsString("Congratulations, You won!"));
        assertThat(game.getNumberOfRounds(), is(equalTo(scoreboard.numberOfRounds())));
        verify(console).announceGameOver(scoreboard);
    }

    @Test
    public void threeLosses() {
        GameEngine game = createGame(new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS}, new Shape[]{Shape.ROCK, Shape.ROCK, Shape.ROCK});
        Scoreboard scoreboard = game.play();

        assertScoreBoard(scoreboard, 0, 3, 0);
        assertThat(game.getNumberOfRounds(), is(equalTo(scoreboard.numberOfRounds())));
        assertThat(outputResult.toString(), containsString("Sorry, You lost! "));
        verify(console).announceGameOver(scoreboard);
    }

    @Test
    public void threeDraws() {
        GameEngine game = createGame(new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS}, new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS});
        Scoreboard scoreboard = game.play();

        assertScoreBoard(scoreboard, 0, 0, 3);
        assertThat(outputResult.toString(), containsString("It was a draw. "));
        assertThat(game.getNumberOfRounds(), is(equalTo(scoreboard.numberOfRounds())));
        verify(console).announceGameOver(scoreboard);
    }

    @Test
    public void threeMixed() {
        GameEngine game = createGame(new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS}, new Shape[]{Shape.SCISSORS, Shape.ROCK, Shape.PAPER});
        Scoreboard scoreboard = game.play();

        assertScoreBoard(scoreboard, 1, 1, 1);
        assertThat(game.getNumberOfRounds(), is(equalTo(scoreboard.numberOfRounds())));
        verify(console).announceGameOver(scoreboard);
    }

    @Test
    public void testQuit() {
        GameEngine game = createGame(new Shape[]{Shape.SCISSORS, Shape.QUIT, Shape.SCISSORS}, new Shape[]{Shape.SCISSORS, Shape.SCISSORS, Shape.SCISSORS});
        Scoreboard scoreboard = game.play();

        assertThat(scoreboard.numberOfRounds(), is(equalTo(1L)));
        verify(console, times(2)).askNextShape();
        verify(console).announceGameOver(scoreboard);
        assertThat(game.getNumberOfRounds(), is(not(equalTo(scoreboard.numberOfRounds()))));
    }


    private void assertScoreBoard(Scoreboard scoreboard, long user, long computer, long draws) {
        assertThat(scoreboard.getUserScore(), is(equalTo(user)));
        assertThat(scoreboard.getComputerScore(), is(equalTo(computer)));
        assertThat(scoreboard.getDrawScore(), is(equalTo(draws)));
    }
}
