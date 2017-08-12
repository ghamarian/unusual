package game;

public interface Console {

    Shape askNextShape();

    void annouceLastRoundWinner(Winner winner);

    int askUserForNumberOfRounds();

    void announceGameOver(Scoreboard scoreboard);

    void announceGuesses(Shape userGuess, Shape computerGuess);
}
