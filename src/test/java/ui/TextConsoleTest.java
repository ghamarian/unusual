package ui;

import game.Console;
import game.Scoreboard;
import game.Shape;
import game.Winner;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.Mockito.*;

public class TextConsoleTest {

    private static final String GAME_OVER = "Game over.";
    private PrintStream outSpy;
    private ByteArrayOutputStream outputResult;
    private TextConsole emptyInputConsole;

    @Before
    public void setUp() {
        outputResult = new ByteArrayOutputStream();
        outSpy = new PrintStream(outputResult);
        emptyInputConsole = spy(new TextConsole(new Scanner(""), outSpy));
    }

    @Test
    public void askNextShape_ShouldRepeatUntilValidShape() {
        TextConsole console = spy(new TextConsole(new Scanner("aa\nabcd\nkashk\nrock\n"), outSpy));
        final Shape shape = console.askNextShape();
        assertThat(shape, is(equalTo(Shape.ROCK)));
        verify(console, times(4)).promptNextInput();
    }

    @Test
    public void askNextShape_shouldMatchExactWord() {
        Console console = new TextConsole(new Scanner("asrocks\npaper\n"), outSpy);
        final Shape shape = console.askNextShape();
        assertThat(shape, is(equalTo(Shape.PAPER)));
    }

    @Test
    public void askNextShape_returnsShapeCorrespondingtoInput() {
        for (Shape shape : Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString()), outSpy);
            assertThat(shape, is(equalTo(console.askNextShape())));
        }
    }

    @Test
    public void caseInsensitiveInputsAreAccepted() {
        for (Shape shape : Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString().toLowerCase()), outSpy);
            assertThat(shape, is(equalTo(console.askNextShape())));
        }
    }

    @Test
    public void askUserForNumberOfRounds_shouldKeepAskingUntilItIsANumber() {
        TextConsole console = spy(new TextConsole(new Scanner("a\nb\n3n\n3\n"), outSpy));

        assertThat(console.askUserForNumberOfRounds(), is(equalTo(3)));
        assertThat(outputResult.toString(), containsString("Please enter a numerical value."));
    }

    @Test
    public void askUserForNumberOfRounds_shouldIgnoreSpaces() throws Exception {
        Console console = new TextConsole(new Scanner("  a\n  4\n"), outSpy);
        assertThat(console.askUserForNumberOfRounds(), is(equalTo(4)));
    }

    @Test
    public void announceGameOver_wonGame() {
        Scoreboard scoreboard = getScoreboardFor(Winner.USER);
        emptyInputConsole.announceGameOver(scoreboard);

        assertThat(outputResult.toString(), containsString(GAME_OVER));
        assertThat(outputResult.toString(), containsString("Congratulations, You won! "));
        assertThat(outputResult.toString(), containsString("Your score 1 vs 0. Out of the total number of 1 rounds."));
    }

    @Test
    public void announceGameOver_lossGame() {
        Scoreboard scoreboard = getScoreboardFor(Winner.COMPUTER);
        emptyInputConsole.announceGameOver(scoreboard);

        assertThat(outputResult.toString(), containsString(GAME_OVER));
        assertThat(outputResult.toString(), containsString("Sorry, You lost! "));
        assertThat(outputResult.toString(), containsString("Your score 0 vs 1. Out of the total number of 1 rounds."));
    }

    @Test
    public void announceGameOver_drawGame() {
        Scoreboard scoreboard = getScoreboardFor(Winner.DRAW);
        emptyInputConsole.announceGameOver(scoreboard);

        assertThat(outputResult.toString(), containsString(GAME_OVER));
        assertThat(outputResult.toString(), containsString("It was a draw. "));
        assertThat(outputResult.toString(), containsString("Your score 0 vs 0. Out of the total number of 1 rounds."));
    }

    @Test
    public void annouceLastRoundWinner_outputWhenUserWins() {
        emptyInputConsole.annouceLastRoundWinner(Winner.USER);

        assertThat(outputResult.toString(), containsString("You won the last round."));
    }

    @Test
    public void annouceLastRoundWinner_outputWhenComputerWins() {
        emptyInputConsole.annouceLastRoundWinner(Winner.COMPUTER);

        assertThat(outputResult.toString(), containsString("Computer won the last round."));
    }

    @Test
    public void annouceLastRoundWinner_outputWhenDraw() {
        emptyInputConsole.annouceLastRoundWinner(Winner.DRAW);

        assertThat(outputResult.toString(),containsString("Last round was a draw!"));
    }

    @Test
    public void testAnnouceGuesses() {
        emptyInputConsole.announceGuesses(Shape.ROCK, Shape.PAPER);

        assertThat(outputResult.toString(), containsString("Your guess ROCK vs computer guess PAPER"));
    }

    @Test
    public void testAnnounceFinalScors() throws Exception {
        emptyInputConsole.announceFinalScores(3, 2);

        assertThat(outputResult.toString(), containsString("Your score 3 vs 2."));
    }

    private Scoreboard getScoreboardFor(Winner... winners) {
        Scoreboard scoreboard = new Scoreboard();
        for (Winner winner : winners) {
            scoreboard.saveRoundResult(winner);
        }
        return scoreboard;
    }
}
