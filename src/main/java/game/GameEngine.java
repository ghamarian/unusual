package game;

public class GameEngine {

    private final Console console;
    private final Guesser guesser;
    private final RoundJudge judge;
    private final Scoreboard scoreboard;
    private long numberOfRounds;
    private boolean gameOver;

    public GameEngine(Console console, Guesser guesser) {
        this.console = console;
        this.guesser = guesser;
        judge = new RoundJudge();
        scoreboard = new Scoreboard();
    }

    long getNumberOfRounds() {
        return numberOfRounds;
    }

    public Scoreboard play() {
        numberOfRounds = console.askUserForNumberOfRounds();
        gameOver = false;

        while (!gameOver()) {
            Shape computerGuess = guessShape();
            Shape userGuess = askNextShape();

            if (userGuess == Shape.QUIT) {
                gameOver = true;
                continue;
            }

            annouceGuesses(computerGuess, userGuess);
            final Winner winner = findWinner(userGuess, computerGuess);
            announceLastRoundWinner(winner);
            saveRoundResult(winner);
        }
        announceGameOver();
        return scoreboard;
    }

    private Shape askNextShape() {
        return console.askNextShape();
    }

    private Shape guessShape() {
        return guesser.nextGuess();
    }

    private Winner findWinner(Shape userShape, Shape computerShape) {
        return judge.judge(userShape, computerShape);
    }

    private void announceLastRoundWinner(Winner winner) {
        console.annouceLastRoundWinner(winner);
    }

    private void announceGameOver() {
        console.announceGameOver(scoreboard);
    }

    private void saveRoundResult(Winner winner) {
        scoreboard.saveRoundResult(winner);
    }

    private boolean gameOver() {
        return scoreboard.numberOfRounds() >= numberOfRounds || gameOver;
    }

    private void annouceGuesses(Shape computerGuess, Shape userGuess) {
        console.announceGuesses(userGuess, computerGuess);
    }

}
