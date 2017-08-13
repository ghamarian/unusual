import game.GameEngine;
import strategy.RandomGuesser;
import ui.TextConsole;

import java.util.Scanner;

public class Game {

    public static void main(String[] args) {
        GameEngine game = new GameEngine(new TextConsole(new Scanner(System.in)), new RandomGuesser());
        game.play();
    }
}
