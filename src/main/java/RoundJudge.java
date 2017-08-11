public class RoundJudge {
    private static RoundJudge instance;

    private RoundJudge(){
        Shape.ROCK.setup(Shape.SCISSORS);
        Shape.PAPER.setup(Shape.ROCK);
        Shape.SCISSORS.setup(Shape.PAPER);
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
