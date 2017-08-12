package game;

//template pattern?
public class GameEngine {

    private final Console console;
    private final Guesser guesser;
    private final RoundJudge judge;
    private final Scoreboard scoreboard;

    public GameEngine(Console console, Guesser guesser) {
        this.console = console;
        this.guesser = guesser;
        judge = new RoundJudge();
        scoreboard = new Scoreboard();
    }

    private Shape askNext() {
        return console.askNext();
    }

    private Shape guess() {
        return guesser.nextGuess();
    }

    private RoundWinner score(Shape first, Shape second) {
        return judge.judge(first, second);
    }

    private void announceLastLevelWinner(RoundWinner roundWinner) {
        console.annouceLastRoundWinner(roundWinner);
    }

    //write tests for this.
    //make it testable.
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

            final RoundWinner roundWinner = score(userGuess, computerGuess);
            scoreboard.saveScore(roundWinner);
            announceLastLevelWinner(roundWinner);
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

}
