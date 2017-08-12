package gameInterface;

import game.Console;
import game.Scoreboard;
import game.Shape;
import game.Winner;
import gameInterface.TextConsole;
import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.never;
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

    @Test
    public void askUserForNumberOfRounds_shouldKeepAskingUntilItIsANumber() throws Exception {
        Console console = new TextConsole(new Scanner("a\nb\n3n\n3\n"));
        assertThat(console.askUserForNumberOfRounds(), is(equalTo(3)));
    }

    @Test
    public void askUserForNumberOfRounds_shouldIgnoreSpaces() throws Exception {
        Console console = new TextConsole(new Scanner("  a\n  4\n"));
        assertThat(console.askUserForNumberOfRounds(), is(equalTo(4)));
    }

    @Test
    public void announceGameOver_wonGameShouldCallAnnounceWonMatch() throws Exception {
        TextConsole console = spy(new TextConsole(new Scanner("")));
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.saveRoundResult(Winner.USER);
        console.announceGameOver(scoreboard);
        verify(console).announceWonMatch();
        verify(console, never()).announceDrawMatch();
        verify(console, never()).announceLostMatch();
    }

    @Test
    public void announceGameOver_wonGameShouldCallAnnounceLostMatch() throws Exception {
        TextConsole console = spy(new TextConsole(new Scanner("")));
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.saveRoundResult(Winner.COMPUTER);
        console.announceGameOver(scoreboard);
        verify(console).announceLostMatch();
        verify(console, never()).announceWonMatch();
        verify(console, never()).announceDrawMatch();
    }

    @Test
    public void announceGameOver_wonGameShouldCallAnnounceDrawMatch() throws Exception {
        TextConsole console = spy(new TextConsole(new Scanner("")));
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.saveRoundResult(Winner.DRAW);
        console.announceGameOver(scoreboard);
        verify(console).announceDrawMatch();
        verify(console, never()).announceLostMatch();
        verify(console, never()).announceWonMatch();
    }
}
