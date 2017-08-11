
public class Paper extends Hand {
    private static Hand instance;

    private Paper(Hand ...hand) {
        super(hand);
    }

    public static Hand getInstance() {
        if (instance == null) {
            instance = new Paper(Rock.getInstance());
        }
        return instance;
    }
}
