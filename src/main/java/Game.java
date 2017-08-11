import java.util.Scanner;

//template pattern?
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

    //write tests for this.
    //keep the scores.
    //change the score from an int to something more useful.
    public void play() {
        int n = textConsole.getHowManyRounds();
        while (n > 0) {
            Shape computerNextGuess = guess();
            Shape userGuess = askNext();

            System.out.println(String.format("Your guess %s vs %s", userGuess, computerNextGuess));
            final int score = score(computerNextGuess, userGuess);
            annouceWinner(score);
            n--;
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
