package game;

public class RoundJudge {

    public RoundJudge(){
        Shape.ROCK.setWeakerShapes(Shape.SCISSORS);
        Shape.PAPER.setWeakerShapes(Shape.ROCK);
        Shape.SCISSORS.setWeakerShapes(Shape.PAPER);
    }

    public Score judge(Shape first, Shape second) {
        return first.winsAgainst(second);
    }

}
