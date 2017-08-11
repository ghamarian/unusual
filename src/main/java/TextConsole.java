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
    public void annouceWinner(int score) {
        if (score > 0) {
            announceLastRound("lost");
        } else if (score < 0) {
            announceLastRound("won");
        } else {
            System.out.println("It was a draw!");
        }
    }

    @Override
    public void announceLastRound(String lastGameResult) {
        System.out.println(String.format("You %s the last round", lastGameResult));
    }

    private String getNextToken() {
        return scanner.next();
    }
}