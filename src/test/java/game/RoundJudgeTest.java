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

    private void assertUserLoses(Shape user, Shape computer) {
        assertThat(roundJudge.judge(user, computer), is(equalTo(Winner.COMPUTER)));
    }

    private void assertUserWins(Shape user, Shape computer) {
        assertThat(roundJudge.judge(user, computer), is(equalTo(Winner.USER)));
    }

    @Test
    public void givenTwoShape_ScoreShouldStayEqual() {
        assertThat(roundJudge.judge(Shape.PAPER, Shape.PAPER), is(equalTo(Winner.DRAW)));
        assertThat(roundJudge.judge(Shape.SCISSORS, Shape.SCISSORS), is(equalTo(Winner.DRAW)));
        assertThat(roundJudge.judge(Shape.ROCK, Shape.ROCK), is(equalTo(Winner.DRAW)));
    }

    @Test
    public void givenPaperAndScissor_ScissorShouldWin() {
        assertUserLoses(Shape.PAPER, Shape.SCISSORS);
        assertUserWins(Shape.SCISSORS, Shape.PAPER);
    }

    @Test
    public void givenPaperAndRock_PaperShouldWin() {
        assertUserWins(Shape.PAPER, Shape.ROCK);
        assertUserLoses(Shape.ROCK, Shape.PAPER);
    }

    @Test
    public void givenScissorAndRock_RockShouldWin() {
        assertUserLoses(Shape.SCISSORS, Shape.ROCK);
        assertUserWins(Shape.ROCK, Shape.SCISSORS);
    }
}
