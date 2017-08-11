import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class GameTest {
    @Test
    public void givenTwoHand_ScoreShouldStayEqual() throws Exception {
        RoundJudge roundJudge = new RoundJudge();
        assertThat(roundJudge.judge(Paper.getInstance(), Paper.getInstance()), is(equalTo(0)));
    }

    @Test
    public void givenPaperAndScissor_ScissorShouldWin() throws Exception {
       RoundJudge roundJudge = new RoundJudge();
       assertThat(roundJudge.judge(Paper.getInstance(), Scissors.getInstance()), is(equalTo(-1)));
        assertThat(roundJudge.judge(Scissors.getInstance(), Paper.getInstance()), is(equalTo(1)));
    }

    @Test
    public void givenPaperAndRock_PaperShouldWin() throws Exception {
        RoundJudge roundJudge = new RoundJudge();
        assertThat(roundJudge.judge(Paper.getInstance(), Rock.getInstance()), is(equalTo(1)));
        assertThat(roundJudge.judge(Rock.getInstance(), Paper.getInstance()), is(equalTo(-1)));
    }

    @Test
    public void givenScissorAndRock_RockShouldWin() throws Exception {
        RoundJudge roundJudge = new RoundJudge();
        assertThat(roundJudge.judge(Scissors.getInstance(), Rock.getInstance()), is(equalTo(-1)));
        assertThat(roundJudge.judge(Rock.getInstance(), Scissors.getInstance()), is(equalTo(1)));
    }
}
