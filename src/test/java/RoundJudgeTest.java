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

    private void assertLosingJudgement(RoundJudge.Hand loser, RoundJudge.Hand winner) {
        assertThat(roundJudge.judge(loser, winner), is(equalTo(-1)));
    }

    private void assertWinningJudgement(RoundJudge.Hand winner, RoundJudge.Hand loser) {
        assertThat(roundJudge.judge(winner, loser), is(equalTo(1)));
    }

    @Test
    public void givenTwoHand_ScoreShouldStayEqual() throws Exception {
        assertThat(roundJudge.judge(RoundJudge.Hand.PAPER, RoundJudge.Hand.PAPER), is(equalTo(0)));
    }

    @Test
    public void givenPaperAndScissor_ScissorShouldWin() throws Exception {
        assertLosingJudgement(RoundJudge.Hand.PAPER, RoundJudge.Hand.SCISSORS);
        assertWinningJudgement(RoundJudge.Hand.SCISSORS, RoundJudge.Hand.PAPER);
    }

    @Test
    public void givenPaperAndRock_PaperShouldWin() throws Exception {
        assertWinningJudgement(RoundJudge.Hand.PAPER, RoundJudge.Hand.ROCK);
        assertLosingJudgement(RoundJudge.Hand.ROCK, RoundJudge.Hand.PAPER);
    }

    @Test
    public void givenScissorAndRock_RockShouldWin() throws Exception {
        assertLosingJudgement(RoundJudge.Hand.SCISSORS, RoundJudge.Hand.ROCK);
        assertWinningJudgement(RoundJudge.Hand.ROCK, RoundJudge.Hand.SCISSORS);
    }
}
