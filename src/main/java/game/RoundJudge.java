package game;

class RoundJudge {

    RoundJudge(){
        Shape.ROCK.setWeakerShapes(Shape.SCISSORS);
        Shape.PAPER.setWeakerShapes(Shape.ROCK);
        Shape.SCISSORS.setWeakerShapes(Shape.PAPER);
    }

    Winner judge(Shape first, Shape second) {
        return first.winsAgainst(second);
    }
}