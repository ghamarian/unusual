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

    //TODO do something about this findWinner, do you need it like this?
    private Winner findWinner(Shape userShape, Shape computerShape) {
        return judge.judge(userShape, computerShape);
    }

    private void announceLastLevelWinner(Winner winner) {
        console.annouceLastRoundWinner(winner);
    }

    public Scoreboard play() {
        int numberOfRounds = console.askUserForNumberOfRounds();
        boolean gameOver = false;

        while (!gameOver(numberOfRounds, gameOver)) {
            Shape computerGuess = guess();
            Shape userGuess = askNext();

            if (userGuess == Shape.QUIT) {
                gameOver = true;
                continue;
            }

            annouceGuesses(computerGuess, userGuess);
            final Winner winner = findWinner(userGuess, computerGuess);
            announceLastLevelWinner(winner);
            scoreboard.saveRoundResult(winner);
            numberOfRounds--;
        }
        console.announceGameOver(scoreboard);
        return scoreboard;
    }

    private boolean gameOver(int remainingTries, boolean gameOver) {
        return remainingTries == 0 || gameOver;
    }

    private void annouceGuesses(Shape computerGuess, Shape userGuess) {
        console.announceGuesses(userGuess, computerGuess);
    }

}
