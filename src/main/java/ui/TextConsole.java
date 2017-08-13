package ui;

import game.Console;
import game.Winner;
import game.Scoreboard;
import game.Shape;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextConsole implements Console {
    private static final String HOW_MANY_ROUNDS_PROMPT = "Please enter how many rounds would you like to play?";
    private static final String SHOW_GUSSES_STATEMENT = "Your guess %s vs computer guess %s";
    private static final String FINAL_SCORE_STATEMENT = "Your score %s vs %s.";
    private static final String PROMPT_ENTER_GUESS_STATEMENT = "Please enter your guess %s: ";
    private static final String ANNOUNCE_NUMBER_OF_ROUNDS_STATEMENT = " Out of the total number of %s rounds.";
    private static final String ANNOUNCE_ROUND_WON_RESULT = "%s won the last round.";
    private static final String ANNOUNCE_ROUND_DRAW_RESULT = "Last round was a draw!";

    static final String ANNOUNCE_DRAW_STATEMENT = "It was a draw. ";
    static final String ANNOUNCE_WIN_STATEMENT = "Congratulations, You won! ";
    static final String ANNOUNCE_LOSS_STATEMENT = "Sorry, You lost! ";
    static final String ANNOUNCE_GAME_OVER_STATEMENT = "Game over.";
    static final String ENTER_NUMERICAL_PROMPT = "Please enter a numerical value.";

    private final PrintStream out;
    private Scanner scanner;
    private Pattern pattern;

    public TextConsole(Scanner scanner, PrintStream out) {
        this.scanner = scanner;
        this.pattern = Pattern.compile(Arrays.stream(Shape.values()).map(Object::toString).collect(Collectors.joining("|")), Pattern.CASE_INSENSITIVE);
        this.out = out;
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

    @Override
    public void annouceLastRoundWinner(Winner winner) {
        if (winner == Winner.DRAW) {
            out.println(ANNOUNCE_ROUND_DRAW_RESULT);
        }
        else {
            promptRoundResult(winner);
        }
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
                out.println(ENTER_NUMERICAL_PROMPT);
                promptUserForNumberOfRounds();
            }
        }
        return result;
    }

    @Override
    public void announceGameOver(Scoreboard scoreboard) {
        out.println(ANNOUNCE_GAME_OVER_STATEMENT);

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

    @Override
    public void announceGuesses(Shape userGuess, Shape computerGuess) {
        out.println(String.format(SHOW_GUSSES_STATEMENT, userGuess, computerGuess));
    }

    private void announceNumberOfRuncs(long numberOfTries) {
        out.println(String.format(ANNOUNCE_NUMBER_OF_ROUNDS_STATEMENT, numberOfTries));
    }

    public void announceLostMatch() {
        out.print(ANNOUNCE_LOSS_STATEMENT);
    }

    public void announceWonMatch() {
        out.print(ANNOUNCE_WIN_STATEMENT);
    }

    public void announceDrawMatch() {
        out.print(ANNOUNCE_DRAW_STATEMENT);
    }

    private void announceFinalScores(long userScore, long computerScore) {
        out.print(String.format(FINAL_SCORE_STATEMENT, userScore, computerScore));
    }

    private void promptUserForNumberOfRounds() {
        out.println(HOW_MANY_ROUNDS_PROMPT);
    }

    private String getNextToken() {
        return scanner.next();
    }

    void promptNextInput() {
        out.println(String.format(PROMPT_ENTER_GUESS_STATEMENT, Arrays.toString(Shape.values())));
    }

    private void promptRoundResult(Winner winner) {
        out.println(String.format(ANNOUNCE_ROUND_WON_RESULT, translate(winner)));
    }

    private String translate(Winner winner) {
        if (winner == Winner.USER) {
            return "You";
        }
        else if (winner == Winner.COMPUTER){
            return "Computer";
        } else return "Everybody";
    }
}