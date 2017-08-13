package ui;

import game.Console;
import game.Scoreboard;
import game.Shape;
import game.Winner;
import org.junit.Test;

import java.util.Scanner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class TextConsoleTest {

    @Test
    public void askNextShape_ShouldRepeatUntilAValidShape() {
       Console console = new TextConsole(new Scanner("aa\nabcd\nkashk\nrock\n"));
       final Shape shape = console.askNextShape();
        assertThat(shape, is(equalTo(Shape.ROCK)));
    }

    @Test
    public void askNextChape_shouldMatchExactWord() {
        Console console = new TextConsole(new Scanner("asrocks\npaper\n"));
        final Shape shape = console.askNextShape();
        assertThat(shape, is(equalTo(Shape.PAPER)));
    }

    @Test
    public void GivenCorrectShape_askNextItShouldReturnThatShape() {
        for (Shape shape : Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString()));
            assertThat(shape, is(equalTo(console.askNextShape())));
        }
    }

    @Test
    public void caseInsensitiveInputsAreAccepted() {
        for (Shape shape : Shape.values()) {
            Console console = new TextConsole(new Scanner(shape.toString().toLowerCase()));
            assertThat(shape, is(equalTo(console.askNextShape())));
        }
    }

    @Test
    public void askUserForNumberOfRounds_shouldKeepAskingUntilItIsANumber() {
        Console console = new TextConsole(new Scanner("a\nb\n3n\n3\n"));
        assertThat(console.askUserForNumberOfRounds(), is(equalTo(3)));
    }

    @Test
    public void askUserForNumberOfRounds_shouldIgnoreSpaces() throws Exception {
        Console console = new TextConsole(new Scanner("  a\n  4\n"));
        assertThat(console.askUserForNumberOfRounds(), is(equalTo(4)));
    }

    @Test
    public void announceGameOver_wonGameShouldCallAnnounceWonMatch() {
        TextConsole console = spy(new TextConsole(new Scanner("")));
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.saveRoundResult(Winner.USER);
        console.announceGameOver(scoreboard);
        verify(console).announceWonMatch();
        verify(console, never()).announceDrawMatch();
        verify(console, never()).announceLostMatch();
    }

    @Test
    public void announceGameOver_wonGameShouldCallAnnounceLostMatch() {
        TextConsole console = spy(new TextConsole(new Scanner("")));
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.saveRoundResult(Winner.COMPUTER);
        console.announceGameOver(scoreboard);
        verify(console).announceLostMatch();
        verify(console, never()).announceWonMatch();
        verify(console, never()).announceDrawMatch();
    }

    @Test
    public void announceGameOver_wonGameShouldCallAnnounceDrawMatch() {
        TextConsole console = spy(new TextConsole(new Scanner("")));
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.saveRoundResult(Winner.DRAW);
        console.announceGameOver(scoreboard);
        verify(console).announceDrawMatch();
        verify(console, never()).announceLostMatch();
        verify(console, never()).announceWonMatch();
    }
}
