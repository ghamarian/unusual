import java.util.Scanner;

//template pattern?
public class Game {
    private final Console console = new TextConsole(new Scanner(System.in));
    private final RandomGuesser guesser;
    private final RoundJudge judge;
    private final Scoreboard scoreboard;

    Game() {
        guesser = new RandomGuesser();
        judge = new RoundJudge();
        scoreboard = new Scoreboard();
    }

    private Shape askNext() {
        return console.askNext();
    }

    private Shape guess() {
        return guesser.nextGuess();
    }

    private Score score(Shape first, Shape second) {
        return judge.judge(first, second);
    }

    private void announceLastLevelWinner(Score score) {
        console.annouceLastRoundWinner(score);
    }

    //write tests for this.
    public void play() {
        int numberOfRounds = console.getHowManyRounds();
        boolean gameOver = false;

        while (!gameOver(numberOfRounds, gameOver)) {
            Shape computerGuess = guess();
            Shape userGuess = askNext();

            if (userGuess == Shape.QUIT) {
                gameOver = true;
                continue;
            }

            annouceGuesses(computerGuess, userGuess);

            final Score score = score(userGuess, computerGuess);
            scoreboard.saveScore(score, userGuess, computerGuess);
            announceLastLevelWinner(score);
            numberOfRounds--;
        }
        console.announceGameOver(scoreboard);
    }

    private boolean gameOver(int remainingTries, boolean gameOver) {
        return remainingTries == 0 || gameOver;
    }

    private void annouceGuesses(Shape computerGuess, Shape userGuess) {
        console.announceGuesses(userGuess, computerGuess);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
