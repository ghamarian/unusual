import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class RoundJudgeTest {
    private RoundJudge roundJudge;

    @Before
    public void setUp() throws Exception {
        roundJudge = new RoundJudge();
    }

    private void assertLosingJudgement(Shape loser, Shape winner) {
        assertThat(roundJudge.judge(loser, winner), is(equalTo(Score.LOST)));
    }

    private void assertWinningJudgement(Shape winner, Shape loser) {
        assertThat(roundJudge.judge(winner, loser), is(equalTo(Score.WON)));
    }

    @Test
    public void givenTwoShape_ScoreShouldStayEqual() throws Exception {
        assertThat(roundJudge.judge(Shape.PAPER, Shape.PAPER), is(equalTo(Score.DRAW)));
    }

    @Test
    public void givenPaperAndScissor_ScissorShouldWin() throws Exception {
        assertLosingJudgement(Shape.PAPER, Shape.SCISSORS);
        assertWinningJudgement(Shape.SCISSORS, Shape.PAPER);
    }

    @Test
    public void givenPaperAndRock_PaperShouldWin() throws Exception {
        assertWinningJudgement(Shape.PAPER, Shape.ROCK);
        assertLosingJudgement(Shape.ROCK, Shape.PAPER);
    }

    @Test
    public void givenScissorAndRock_RockShouldWin() throws Exception {
        assertLosingJudgement(Shape.SCISSORS, Shape.ROCK);
        assertWinningJudgement(Shape.ROCK, Shape.SCISSORS);
    }
}
