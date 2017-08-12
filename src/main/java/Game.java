import java.util.Scanner;

//template pattern?
public class Game {
    private final Console textConsole = new TextConsole(new Scanner(System.in));
    private final RandomGuesser guesser;
    private final RoundJudge judge;
    private final Scoreboard scoreboard;

    Game() {
        guesser = new RandomGuesser();
        judge = new RoundJudge();
        scoreboard  = new Scoreboard();
    }

    private Shape askNext() {
        return textConsole.askNext();
    }

    private Shape guess() {
        return guesser.nextGuess();
    }

    private Score score(Shape first, Shape second) {
        return judge.judge(first, second);
    }

    private void annouceWinner(Score score) {
        textConsole.annouceLastRoundWinner(score);
    }

    //write tests for this.
    //keep the scores.
    //change the score from an int to something more useful.
    public void play() {
        int n = textConsole.getHowManyRounds();
        while (n > 0) {
            Shape computerGuess = guess();
            Shape userGuess = askNext();

            //move to console.
            System.out.println(String.format("Your guess %s vs computer guess %s", userGuess, computerGuess));

            final Score score = score(userGuess, computerGuess);
            scoreboard.saveScore(score, userGuess, computerGuess);
            annouceWinner(score);

            n--;
        }
        textConsole.announceGameOver();
    }


    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
