package game;

class RoundJudge {

    RoundJudge(){
        Shape.ROCK.setLooserShapes(Shape.SCISSORS);
        Shape.PAPER.setLooserShapes(Shape.ROCK);
        Shape.SCISSORS.setLooserShapes(Shape.PAPER);
    }

    Winner judge(Shape userShape, Shape computerShape) {
        if (userShape == computerShape){
           return Winner.DRAW;
        }
        return userShape.winsAgainst(computerShape) ? Winner.USER : Winner.COMPUTER;
    }
}
