import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class RoundJudgeTest {
    private RoundJudge roundJudge;

    @Before
    public void setUp() throws Exception {
        roundJudge = RoundJudge.getInstance();
    }

    private void assertLosingJudgement(RoundJudge.Shape loser, RoundJudge.Shape winner) {
        assertThat(roundJudge.judge(loser, winner), is(equalTo(-1)));
    }

    private void assertWinningJudgement(RoundJudge.Shape winner, RoundJudge.Shape loser) {
        assertThat(roundJudge.judge(winner, loser), is(equalTo(1)));
    }

    @Test
    public void givenTwoShape_ScoreShouldStayEqual() throws Exception {
        assertThat(roundJudge.judge(RoundJudge.Shape.PAPER, RoundJudge.Shape.PAPER), is(equalTo(0)));
    }

    @Test
    public void givenPaperAndScissor_ScissorShouldWin() throws Exception {
        assertLosingJudgement(RoundJudge.Shape.PAPER, RoundJudge.Shape.SCISSORS);
        assertWinningJudgement(RoundJudge.Shape.SCISSORS, RoundJudge.Shape.PAPER);
    }

    @Test
    public void givenPaperAndRock_PaperShouldWin() throws Exception {
        assertWinningJudgement(RoundJudge.Shape.PAPER, RoundJudge.Shape.ROCK);
        assertLosingJudgement(RoundJudge.Shape.ROCK, RoundJudge.Shape.PAPER);
    }

    @Test
    public void givenScissorAndRock_RockShouldWin() throws Exception {
        assertLosingJudgement(RoundJudge.Shape.SCISSORS, RoundJudge.Shape.ROCK);
        assertWinningJudgement(RoundJudge.Shape.ROCK, RoundJudge.Shape.SCISSORS);
    }
}
