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

        public Score getScore() {
            return score;
        }
    }

    private List<RoundResult> history = new ArrayList<>();

    public int numberOfTries() {
        return history.size();
    }

    public void saveScore(Score score, Shape userShape, Shape computerShape) {
        history.add(new RoundResult(score, userShape, computerShape));
    }

    public void clear(){
        history.clear();
    }

    private Integer countScore(Score won) {
        return history.stream().reduce(0, (acc, roundResult) -> roundResult.getScore() == won ? acc + 1 : acc, (x, y) -> x + y);
    }

    public int getUserScore() {
        return countScore(Score.WON);
    }

    public int getComputerScore() {
        return countScore(Score.LOST);
    }

    public int getDraws() {
        return countScore(Score.DRAW);
    }

    public int getUserScoreloop() {
        int userWins = 0;
        for (RoundResult roundResult : history) {
            if (roundResult.score == Score.WON) userWins++;
        }
        return userWins;
    }

}
