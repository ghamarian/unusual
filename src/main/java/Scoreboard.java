import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
    class RoundResult {
        Score score;
        Shape userShape;
        Shape computerShape;

        RoundResult(Score score, Shape userShape, Shape computerShape) {
            this.score = score;
            this.userShape = userShape;
            this.computerShape = computerShape;
        }
    }

    private List<RoundResult> history = new ArrayList<>();

    public void saveScore(Score score, Shape userShape, Shape computerShape) {
        history.add(new RoundResult(score, userShape, computerShape));
    }

}
