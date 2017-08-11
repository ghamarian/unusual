
public class Rock extends Hand {
   private static Hand instance;

   private Rock(Hand ...hand) {
      super(hand);
   }

   public static Hand getInstance() {
      if (instance == null) {
         instance = new Rock(Scissors.getInstance());
      }
      return instance;
   }
}
