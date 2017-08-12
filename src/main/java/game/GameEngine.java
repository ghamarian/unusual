package game;

public class GameEngine {

    private final Console console;
    private final Guesser guesser;
    private final RoundJudge judge;
    private final Scoreboard scoreboard;
    private int numberOfRounds;
    private boolean gameOver;

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

    private Winner findWinner(Shape userShape, Shape computerShape) {
        return judge.judge(userShape, computerShape);
    }

    private void announceLastLevelWinner(Winner winner) {
        console.annouceLastRoundWinner(winner);
    }

    public Scoreboard play() {
        numberOfRounds = console.askUserForNumberOfRounds();
        gameOver = false;

        while (!gameOver()) {
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
        }
        console.announceGameOver(scoreboard);
        return scoreboard;
    }

    private boolean gameOver() {
        return scoreboard.numberOfRounds() >= numberOfRounds || gameOver;
    }

    private void annouceGuesses(Shape computerGuess, Shape userGuess) {
        console.announceGuesses(userGuess, computerGuess);
    }

}
