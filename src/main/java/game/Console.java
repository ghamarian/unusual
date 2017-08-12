package game;

public interface Console {

    Shape askNext();

    void promptNextInput();

    void annouceLastRoundWinner(Winner winner);

    int askUserForNumberOfRounds();

    void announceGameOver(Scoreboard scoreboard);

    void announceGuesses(Shape userGuess, Shape computerGuess);
}
