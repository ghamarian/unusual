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
        System.out.println("Please enter your guess: [Paper, Scissors or Rock]: ");
        String next = getNextToken().toUpperCase();
        Matcher matcher = pattern.matcher(next);
        while (!matcher.find()) {
            System.out.println("Please enter your guess: [Paper, Scissors or Rock]: ");
            next = getNextToken().toUpperCase();
            matcher = pattern.matcher(next);
        }
        return Shape.valueOf(matcher.group());
    }

    protected String getNextToken() {
        return scanner.next();
    }

    @Override
    public void annouceWinner(int score) {
        if (score > 0) {
            System.out.println("You lost the last round");
        } else if (score < 0) {
            System.out.println("You won the last round");
        } else {
            System.out.println("It was a draw!");
        }
    }
}