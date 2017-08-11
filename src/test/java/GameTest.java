import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;



public class GameTest {
    private RoundJudge roundJudge;

    @Before
    public void setUp() throws Exception {
        roundJudge = RoundJudge.getInstance();
    }

    @Test
    public void givenTwoHand_ScoreShouldStayEqual() throws Exception {
        assertThat(roundJudge.judge(RoundJudge.Hand.PAPER, RoundJudge.Hand.PAPER), is(equalTo(0)));
    }

    @Test
    public void givenPaperAndScissor_ScissorShouldWin() throws Exception {
       assertThat(roundJudge.judge(RoundJudge.Hand.PAPER, RoundJudge.Hand.SCISSORS), is(equalTo(-1)));
        assertThat(roundJudge.judge(RoundJudge.Hand.SCISSORS, RoundJudge.Hand.PAPER), is(equalTo(1)));
    }

    @Test
    public void givenPaperAndRock_PaperShouldWin() throws Exception {
        assertThat(roundJudge.judge(RoundJudge.Hand.PAPER, RoundJudge.Hand.ROCK), is(equalTo(1)));
        assertThat(roundJudge.judge(RoundJudge.Hand.ROCK, RoundJudge.Hand.PAPER), is(equalTo(-1)));
    }

    @Test
    public void givenScissorAndRock_RockShouldWin() throws Exception {
        assertThat(roundJudge.judge(RoundJudge.Hand.SCISSORS, RoundJudge.Hand.ROCK), is(equalTo(-1)));
        assertThat(roundJudge.judge(RoundJudge.Hand.ROCK, RoundJudge.Hand.SCISSORS), is(equalTo(1)));
    }
}
