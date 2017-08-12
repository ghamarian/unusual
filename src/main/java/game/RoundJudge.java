package game;

class RoundJudge {

    RoundJudge(){
        Shape.ROCK.setWeakerShapes(Shape.SCISSORS);
        Shape.PAPER.setWeakerShapes(Shape.ROCK);
        Shape.SCISSORS.setWeakerShapes(Shape.PAPER);
    }

    Score judge(Shape first, Shape second) {
        return first.winsAgainst(second);
    }
}
