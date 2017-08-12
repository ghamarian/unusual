package gameInterface;

import game.Console;
import game.Score;
import game.Scoreboard;
import game.Shape;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextConsole implements Console {
    private Scanner scanner;
    private Pattern pattern;

    public TextConsole(Scanner scanner) {
        this.scanner = scanner;
        this.pattern = Pattern.compile(Arrays.stream(Shape.values()).map(Object::toString).collect(Collectors.joining("|")), Pattern.CASE_INSENSITIVE);
    }

    @Override
    public Shape askNext() {
        Matcher matcher;
        do {
            promptNextInput();
            String next = getNextToken().toUpperCase();
            matcher = pattern.matcher(next);
        }
        while (!matcher.matches());
        return Shape.valueOf(matcher.group());
    }

    @Override
    public void promptNextInput() {
        System.out.println("Please enter your guess: " + Arrays.toString(Shape.values()));
    }

    //TODO: make string values constant
    @Override
    public void annouceLastRoundWinner(Score score) {
        if (score == Score.LOST) {
            promptRoundResult("lost");
        } else if (score == Score.WON) {
            promptRoundResult("won");
        } else {
            System.out.println("It was a draw!");
        }
    }

    private void promptRoundResult(String lastGameResult) {
        System.out.println(String.format("You %s the last round", lastGameResult));
    }

    @Override
    public int getHowManyRounds() {
        promptUserForNumberOfRounds();
        int result = -1;
        while (result < 0) {
            try {
                final String next = scanner.next();
                result = Integer.parseInt(next);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a numerical value.");
                promptUserForNumberOfRounds();
            }
        }
        return result;
    }

    @Override
    public void promptUserForNumberOfRounds() {
        System.out.println("Please enter how many rounds would you like to play?");
    }

    @Override
    public void announceGameOver(Scoreboard scoreboard) {
        System.out.println("game.GameEngine Over.");
        long userScore = scoreboard.getUserScore();
        long computerScore = scoreboard.getComputerScore();

        if (userScore > computerScore) {
            System.out.print(String.format("Congratulations, You won! Your score %s vs %s.", userScore, computerScore));
        } else if (userScore < computerScore) {
            System.out.print(String.format("Sorry, You Lost! Your score %s vs %s.", userScore, computerScore));
        }
        else {
            System.out.print(String.format("It was a draw with %s(s) wins each.", userScore));
        }
        System.out.println(String.format(" Out of the total number of %s rounds.", scoreboard.numberOfTries()));
    }

    @Override
    public void announceGuesses(Shape userGuess, Shape computerGuess) {
        System.out.println(String.format("Your guess %s vs computer guess %s", userGuess, computerGuess));
    }

    private String getNextToken() {
        return scanner.next();
    }
}