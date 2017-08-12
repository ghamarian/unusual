package game;

import gameInterface.TextConsole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GameEngineEngineTest {

    @Mock
    Guesser guesser;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void threeWins() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.PAPER).thenReturn(Shape.PAPER).thenReturn(Shape.PAPER);
        GameEngine game = new GameEngine(new TextConsole(new Scanner("3\nrock\nrock\nrock\n")), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 0, 3, 0);
    }

    @Test
    public void threeLosses() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(new TextConsole(new Scanner("3\nrock\nrock\nrock\n")), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 3, 0, 0);
    }

    @Test
    public void threeDraws() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(new TextConsole(new Scanner("3\nscissors\nscissors\nscissors\n")), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 0, 0, 3);
    }

    @Test
    public void threeMixed() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(new TextConsole(new Scanner("3\nscissors\nrock\npaper\n")), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 1, 1, 1);
    }

    @Test
    public void testQuit() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS).thenReturn(Shape.SCISSORS);
        GameEngine game = new GameEngine(new TextConsole(new Scanner("3\nscissors\nrock\nquit\n")), guesser);
        Scoreboard scoreboard = game.play();
        assertScoreBoard(scoreboard, 1, 0, 1);
        assertThat(scoreboard.numberOfRounds(), is(equalTo(2L)));
    }


    private void assertScoreBoard(Scoreboard scoreboard, long user, long computer, long draws) {
        assertThat(scoreboard.getUserScore(), is(equalTo(user)));
        assertThat(scoreboard.getComputerScore(), is(equalTo(computer)));
        assertThat(scoreboard.getDraws(), is(equalTo(draws)));
    }

}
