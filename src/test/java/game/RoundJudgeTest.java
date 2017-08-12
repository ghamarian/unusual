package game;

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

    private void assertUserLoses(Shape loser, Shape winner) {
        assertThat(roundJudge.judge(loser, winner), is(equalTo(Winner.COMPUTER)));
    }

    private void assertUserWins(Shape winner, Shape loser) {
        assertThat(roundJudge.judge(winner, loser), is(equalTo(Winner.USER)));
    }

    @Test
    public void givenTwoShape_ScoreShouldStayEqual() throws Exception {
        assertThat(roundJudge.judge(Shape.PAPER, Shape.PAPER), is(equalTo(Winner.DRAW)));
    }

    @Test
    public void givenPaperAndScissor_ScissorShouldWin() throws Exception {
        assertUserLoses(Shape.PAPER, Shape.SCISSORS);
        assertUserWins(Shape.SCISSORS, Shape.PAPER);
    }

    @Test
    public void givenPaperAndRock_PaperShouldWin() throws Exception {
        assertUserWins(Shape.PAPER, Shape.ROCK);
        assertUserLoses(Shape.ROCK, Shape.PAPER);
    }

    @Test
    public void givenScissorAndRock_RockShouldWin() throws Exception {
        assertUserLoses(Shape.SCISSORS, Shape.ROCK);
        assertUserWins(Shape.ROCK, Shape.SCISSORS);
    }
}
