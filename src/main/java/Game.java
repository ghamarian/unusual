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
        scoreboard = new Scoreboard();
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

    private void announceLastLevelWinner(Score score) {
        textConsole.annouceLastRoundWinner(score);
    }

    //write tests for this.
    public void play() {
        int n = textConsole.getHowManyRounds();
        TerminationManager terminationManager = new TerminationManager(n);
        while (!terminationManager.gameOver()) {
            Shape computerGuess = guess();
            Shape userGuess = askNext();

            annouceGuesses(computerGuess, userGuess);

            final Score score = score(userGuess, computerGuess);
            scoreboard.saveScore(score, userGuess, computerGuess);
            announceLastLevelWinner(score);
            n--;
        }
        textConsole.announceGameOver(scoreboard.summarizeScore());
    }

    private void annouceGuesses(Shape computerGuess, Shape userGuess) {
        textConsole.announceGuesses(userGuess, computerGuess);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
