import java.util.Scanner;

public class Game {
    private final Console textConsole = new TextConsole(new Scanner(System.in));
    private final RandomGuesser guesser;

    Game() {
        guesser = new RandomGuesser();
    }

    private Shape askNext() {
        return textConsole.askNext();
    }

    private Shape guess() {
        return guesser.nextGuess();
    }

    private int score(Shape first, Shape second) {
        return RoundJudge.getInstance().judge(first, second);
    }

    private void annouceWinner(int score) {
        textConsole.annouceWinner(score);
    }

    public void play() {
        int n = 10;
        while (n > 0) {
            Shape randomGuess = guess();
            Shape userGuess = askNext();

            System.out.println("Comparing " + randomGuess + ", " + userGuess);
            final int score = score(randomGuess, userGuess);
            System.out.println(score);
            annouceWinner(score);
            n--;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
