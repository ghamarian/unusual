public interface Console {

    Shape askNext();

    void promptNextInput();

    void annouceWinner(int score);

    void announceLastRound(String lastGameResult);

    int getHowManyRounds();

    void promptUserForNumberOfRounds();
}
