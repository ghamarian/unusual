import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Game {
    private Scanner scanner;
    private Pattern pattern;
    private RoundJudge roundJudge;

    public Game() {
        pattern = Pattern.compile(Arrays.stream(RoundJudge.Hand.values()).map(Object::toString).collect(Collectors.joining("|")), Pattern.CASE_INSENSITIVE);
        scanner = new Scanner(System.in);
        roundJudge = RoundJudge.getInstance();
    }

    public RoundJudge.Hand askNext() {
        System.out.println("Please enter your guess: [Paper, Scissors or Rock]: ");
        String next = scanner.next().toUpperCase();
        Matcher matcher = pattern.matcher(next);
        while (!matcher.find()) {
            System.out.println("Please enter your guess: [Paper, Scissors or Rock]: ");
            next = scanner.next().toUpperCase();
            matcher = pattern.matcher(next);
        }
        return RoundJudge.Hand.valueOf(matcher.group());
    }

    public RoundJudge.Hand guess() {
        RandomGuesser guesser = new RandomGuesser();
        return guesser.nextGuess();
    }

    public int score(RoundJudge.Hand first, RoundJudge.Hand second) {
        return roundJudge.judge(first, second);
    }

    public void annouceWinner(int score) {
        if (score > 0) {
            System.out.println("You lost the last round");
        }
        else if (score < 0) {
            System.out.println("You won the last round");
        }
        else {
            System.out.println("It was a draw!");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        int n = 10;
        while (n > 0) {
            RoundJudge.Hand randomGuess = game.guess();
            RoundJudge.Hand userGuess = game.askNext();
            System.out.println("Comparing " + randomGuess + ", " + userGuess);
            final int score = game.score(randomGuess, userGuess);
            System.out.println(score);
            game.annouceWinner(score);
            n--;
        }
    }
}
