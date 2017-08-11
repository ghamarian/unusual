import java.util.Scanner;

public class Game {
    private final Console textConsole = new TextConsole(new Scanner(System.in));
    private RoundJudge roundJudge;

    public Game() {
        roundJudge = RoundJudge.getInstance();
    }

    public RoundJudge.Hand askNext() {
        return textConsole.askNext();
    }

    public RoundJudge.Hand guess() {
        RandomGuesser guesser = new RandomGuesser();
        return guesser.nextGuess();
    }

    public int score(RoundJudge.Hand first, RoundJudge.Hand second) {
        return roundJudge.judge(first, second);
    }

    public void annouceWinner(int score) {
        textConsole.annouceWinner(score);
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
