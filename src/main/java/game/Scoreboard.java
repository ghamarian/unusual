package game;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {

    private List<Winner> history = new ArrayList<>();

    public long numberOfTries() {
        return history.size();
    }

    public void saveRoundResult(Winner winner){
        history.add(winner);
    }

    void clearHistory(){
        history.clear();
    }

    private long countScore(Winner won) {
        return history.stream().filter(x -> x == won).count();
    }

    public long getComputerScore() {
        return countScore(Winner.COMPUTER);
    }

    public long getDraws() {
        return countScore(Winner.DRAW);
    }

    public long getUserScore() {
        return countScore(Winner.USER);
    }

    public Winner getWinner() {
        long userScore = getUserScore();
        long computerScore = getComputerScore();

        if (userScore == computerScore) {
            return Winner.DRAW;
        } else return (userScore > computerScore) ? Winner.USER : Winner.COMPUTER;
    }

    long getUserScoreloop() {
        long userWins = 0;
        for (Winner winner : history) {
            if (winner == Winner.USER) {
                userWins++;
            }
        }
        return userWins;
    }
}
