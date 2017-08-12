import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextConsole implements Console {
    private Scanner scanner;
    private Pattern pattern;

    TextConsole(Scanner scanner) {
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
        return scanner.nextInt();
    }

    @Override
    public void promptUserForNumberOfRounds() {
        System.out.println("Please enter how many rounds would you like to play?");
    }

    private String getNextToken() {
        return scanner.next();
    }

    @Override
    public void announceGameOver(Scoreboard.ScoreSummary scoreSummary) {
        System.out.println("Game Over.");
        Score finalScore = scoreSummary.getFinalScore();
        if (finalScore == Score.WON) {
            System.out.println("You won the game." );

        }
        System.out.println();
    }

    @Override
    public void announceGuesses(Shape userGuess, Shape computerGuess){
        System.out.println(String.format("Your guess %s vs computer guess %s", userGuess, computerGuess));
    }
}