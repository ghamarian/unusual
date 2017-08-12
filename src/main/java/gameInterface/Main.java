package gameInterface;

import game.GameEngine;
import strategy.RandomGuesser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameEngine game = new GameEngine(new TextConsole(new Scanner(System.in)), new RandomGuesser());
        game.play();
    }
}
