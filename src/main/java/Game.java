import java.util.Scanner;

public class Game {
    private final Console textConsole = new TextConsole(new Scanner(System.in));

    public RoundJudge.Shape askNext() {
        return textConsole.askNext();
    }

    public RoundJudge.Shape guess() {
        RandomGuesser guesser = new RandomGuesser();
        return guesser.nextGuess();
    }

    public int score(RoundJudge.Shape first, RoundJudge.Shape second) {
        return RoundJudge.getInstance().judge(first, second);
    }

    public void annouceWinner(int score) {
        textConsole.annouceWinner(score);
    }

    public static void main(String[] args) {
        Game game = new Game();
        int n = 10;
        while (n > 0) {
            RoundJudge.Shape randomGuess = game.guess();
            RoundJudge.Shape userGuess = game.askNext();
            System.out.println("Comparing " + randomGuess + ", " + userGuess);
            final int score = game.score(randomGuess, userGuess);
            System.out.println(score);
            game.annouceWinner(score);
            n--;
        }
    }
}
