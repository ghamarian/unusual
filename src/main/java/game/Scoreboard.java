package game;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private List<RoundWinner> history = new ArrayList<>();

    public long numberOfTries() {
        return history.size();
    }

    void saveScore(RoundWinner roundWinner){
        history.add(roundWinner);
    }

    void clear(){
        history.clear();
    }

    private long countScore(RoundWinner won) {
        return history.stream().filter(x -> x == won).count();
    }

    public long getUserScore() {
        return countScore(RoundWinner.USER);
    }

    public long getComputerScore() {
        return countScore(RoundWinner.COMPUTER);
    }

    public long getDraws() {
        return countScore(RoundWinner.DRAW);
    }

    long getUserScoreloop() {
        long userWins = 0;
        for (RoundWinner roundWinner : history) {
            if (roundWinner == RoundWinner.USER) {
                userWins++;
            }
        }
        return userWins;
    }
}
