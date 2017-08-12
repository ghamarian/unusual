public class RoundJudge {

    public RoundJudge(){
        Shape.ROCK.setWeakerShapes(Shape.SCISSORS);
        Shape.PAPER.setWeakerShapes(Shape.ROCK);
        Shape.SCISSORS.setWeakerShapes(Shape.PAPER);
    }

    Score judge(Shape first, Shape second) {
        if (first == second) {
            return Score.DRAW;
        }
        if (first.winsAgainst(second)) {
            return Score.WON;
        }
        else return Score.LOST;
    }

}
