package game;

import game.Guesser;
import game.RoundJudge;
import game.Scoreboard;
import game.Shape;
import org.junit.Test;
import strategy.RandomGuesser;

import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ScoreboardTest {

    @Test
    public void getUserScore() throws Exception {
        Scoreboard scoreboard = new Scoreboard();
        Guesser guesser = new RandomGuesser();
        RoundJudge judge = new RoundJudge();

        for (int i = 0; i < 100; i++) {
            generateRandomGame(scoreboard, guesser, judge);
            assertThat(scoreboard.getUserScore(), is(equalTo(scoreboard.getUserScoreloop())));
            assertThat(scoreboard.numberOfTries(), is(equalTo(scoreboard.getComputerScore() + scoreboard.getDraws() + scoreboard.getUserScore())));
            scoreboard.clear();
        }
    }

    private void generateRandomGame(Scoreboard scoreboard, Guesser guesser, RoundJudge judge) {
        Random random = new Random();
        for (int i = 0; i < random.nextInt(10); i++) {
            final Shape userGuess = guesser.nextGuess();
            final Shape computerGuess = guesser.nextGuess();
            scoreboard.saveScore(judge.judge(userGuess, computerGuess), userGuess, computerGuess);
        }
    }

}