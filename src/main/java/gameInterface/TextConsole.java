package gameInterface;

import game.Console;
import game.Winner;
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
    public Shape askNextShape() {
        Matcher matcher;
        do {
            promptNextInput();
            String next = getNextToken().toUpperCase();
            matcher = pattern.matcher(next);
        }
        while (!matcher.matches());
        return Shape.valueOf(matcher.group());
    }

    private void promptNextInput() {
        System.out.println("Please enter your guess: " + Arrays.toString(Shape.values()));
    }

    @Override
    public void annouceLastRoundWinner(Winner winner) {
        if (winner == Winner.DRAW) {
            System.out.println("Last round was a draw!");
        }
        else {
            promptRoundResult(winner);
        }
    }

    private void promptRoundResult(Winner winner) {
        System.out.println(String.format("%s won the last round.", translate(winner)));
    }

    private String translate(Winner winner) {
        if (winner == Winner.USER) {
            return "You";
        }
        else if (winner == Winner.COMPUTER){
            return "Computer";
        } else return "Everybody";
    }

    @Override
    public int askUserForNumberOfRounds() {
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
    public void announceGameOver(Scoreboard scoreboard) {
        System.out.println("Game over.");

        long userScore = scoreboard.getUserScore();
        long computerScore = scoreboard.getComputerScore();
        final Winner winner = scoreboard.getWinner();

        if (winner == Winner.DRAW) {
            announceDrawMatch();
        }
        else if (winner == Winner.USER){
            announceWonMatch();
        }
        else {
            announceLostMatch();
        }

        announceFinalScores(userScore, computerScore);
        announceNumberOfRuncs(scoreboard.numberOfRounds());
    }

    private void announceNumberOfRuncs(long numberOfTries) {
        System.out.println(String.format(" Out of the total number of %s rounds.", numberOfTries));
    }

    protected void announceLostMatch() {
        System.out.print("Sorry, You lost! ");
    }

    protected void announceWonMatch() {
        System.out.print("Congratulations, You won! ");
    }

    protected void announceDrawMatch() {
        System.out.print("It was a draw.");
    }

    private void announceFinalScores(long userScore, long computerScore) {
        System.out.print(String.format("Your score %s vs %s.", userScore, computerScore));
    }

    @Override
    public void announceGuesses(Shape userGuess, Shape computerGuess) {
        System.out.println(String.format("Your guess %s vs computer guess %s", userGuess, computerGuess));
    }

    private void promptUserForNumberOfRounds() {
        System.out.println("Please enter how many rounds would you like to play?");
    }

    private String getNextToken() {
        return scanner.next();
    }
}