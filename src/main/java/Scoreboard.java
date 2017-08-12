import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    static class RoundResult {
        Score score;
        Shape userShape;
        Shape computerShape;

        RoundResult(Score score, Shape userShape, Shape computerShape) {
            this.score = score;
            this.userShape = userShape;
            this.computerShape = computerShape;
        }
    }

    public static class ScoreSummary {
       int userWins;
       int computerWins;
       int draws;

        public ScoreSummary(int userWins, int computerWins, int draws) {
            this.userWins = userWins;
            this.computerWins = computerWins;
            this.draws = draws;
        }

        public Score getFinalScore() {
            if (userWins == computerWins) {
                return Score.DRAW;
            }
            return (userWins > computerWins) ? Score.WON : Score.LOST;
        }
    }

    private List<RoundResult> history = new ArrayList<>();

    public void saveScore(Score score, Shape userShape, Shape computerShape) {
        history.add(new RoundResult(score, userShape, computerShape));
    }

    public ScoreSummary summarizeScore() {
        int userWins = 0;
        int computerWins = 0;
        int draws = 0;
        for (RoundResult roundResult : history) {
           if (roundResult.score == Score.WON) {
               ++userWins;
           }
           else if (roundResult.score == Score.LOST) {
               ++computerWins;
           }
           else {
               ++draws;
           }
        }
        return new ScoreSummary(userWins, computerWins, draws);
    }

}
