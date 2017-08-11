public interface Console {
    RoundJudge.Hand askNext();

    void annouceWinner(int score);
}
