package game;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    static class RoundResult {
        private Score score;
        private Shape userShape;
        private Shape computerShape;

        RoundResult(Score score, Shape userShape, Shape computerShape) {
            this.score = score;
            this.userShape = userShape;
            this.computerShape = computerShape;
        }

        Score getScore() {
            return score;
        }
    }

    private List<RoundResult> history = new ArrayList<>();

    public long numberOfTries() {
        return history.size();
    }

    void saveScore(Score score, Shape userShape, Shape computerShape) {
        history.add(new RoundResult(score, userShape, computerShape));
    }

    void clear(){
        history.clear();
    }

    private long countScore(Score won) {
        return history.stream().filter(x -> x.getScore() == won).count();
    }

    public long getUserScore() {
        return countScore(Score.WON);
    }

    public long getComputerScore() {
        return countScore(Score.LOST);
    }

    public long getDraws() {
        return countScore(Score.DRAW);
    }

    long getUserScoreloop() {
        long userWins = 0;
        for (RoundResult roundResult : history) {
            if (roundResult.score == Score.WON) userWins++;
        }
        return userWins;
    }
}
