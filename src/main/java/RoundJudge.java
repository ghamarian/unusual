public class RoundJudge {
    private static RoundJudge instance;

    private RoundJudge(){
        Shape.ROCK.setWeakerShapes(Shape.SCISSORS);
        Shape.PAPER.setWeakerShapes(Shape.ROCK);
        Shape.SCISSORS.setWeakerShapes(Shape.PAPER);
    }

    public static RoundJudge getInstance() {
        if (instance == null) {
            instance = new RoundJudge();
        }
        return instance;
    }

    int judge(Shape first, Shape second) {
        if (first == second) {
            return 0;
        }
        if (first.winsAgainst(second)) {
            return 1;
        }
        else return -1;
    }
}
