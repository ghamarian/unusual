public class TerminationManager {
    int numberOfTries;

    public TerminationManager(int numberOfTries) {
        this.numberOfTries = numberOfTries;
    }

    boolean gameOver() {
        if (numberOfTries == 0) {
            return true;
        }
        numberOfTries--;
        return false;
    }
}
