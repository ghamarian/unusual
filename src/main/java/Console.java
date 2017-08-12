public interface Console {

    Shape askNext();

    void promptNextInput();

    void annouceLastRoundWinner(Score score);

    int getHowManyRounds();

    void promptUserForNumberOfRounds();

    void announceGameOver(Scoreboard.ScoreSummary scoreboard);

    void announceGuesses(Shape userGuess, Shape computerGuess);
}
