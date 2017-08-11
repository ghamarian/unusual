
public class Scissors extends Hand {
    private static Hand instance;

    private Scissors(Hand... hand) {
        super(hand);
    }

    public static Hand getInstance() {
        if (instance == null) {
            instance = new Scissors(Paper.getInstance());
        }
        return instance;
    }
}
