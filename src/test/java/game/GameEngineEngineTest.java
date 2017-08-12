package game;

import gameInterface.TextConsole;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Scanner;

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
    public void play() throws Exception {
        when(guesser.nextGuess()).thenReturn(Shape.PAPER).thenReturn(Shape.PAPER).thenReturn(Shape.PAPER);
        GameEngine game = new GameEngine(new TextConsole(new Scanner("3\nrock\nrock\nrock\n")), guesser);
        game.play();
    }
}
