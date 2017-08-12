package game;

import gameInterface.TextConsole;
import org.junit.Before;
import org.junit.Test;
import strategy.RandomGuesser;

import java.util.Scanner;

public class GameEngineEngineTest {

    @Before
    public void setUp() throws Exception {
        GameEngine game = new GameEngine(new TextConsole(new Scanner(System.in)), new RandomGuesser());
    }

    @Test
    public void play() throws Exception {

    }
}
