package game;

public interface Console {

    Shape askNext();

    void promptNextInput();

    void annouceLastRoundWinner(RoundWinner roundWinner);

    int getHowManyRounds();

    void promptUserForNumberOfRounds();

    void announceGameOver(Scoreboard scoreboard);

    void announceGuesses(Shape userGuess, Shape computerGuess);
}
