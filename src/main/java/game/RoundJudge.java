package game;

class RoundJudge {

    RoundJudge(){
        Shape.ROCK.setWeakerShapes(Shape.SCISSORS);
        Shape.PAPER.setWeakerShapes(Shape.ROCK);
        Shape.SCISSORS.setWeakerShapes(Shape.PAPER);
    }

    Winner judge(Shape user, Shape computer) {
        if (user == computer){
           return Winner.DRAW;
        }
        return user.winsAgainst(computer) ? Winner.USER : Winner.COMPUTER;
    }
}
