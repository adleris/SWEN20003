import bagel.*;
import bagel.util.*;
import utilities.*;

public class Peg extends Entity {
    private final String imgPath = "res/peg.jpg";

    /**
     * Constructor for pegs
     */
    public Peg() {
        double x = randomInRange(ShadowBounce.SCREEN_X_MIN, ShadowBounce.SCREEN_X_MAX);
        double y = randomInRange(ShadowBounce.SCREEN_Y_MIN, ShadowBounce.SCREEN_Y_MAX);
        super.point = new Point(x,y);
    }

    /**
     *  Can't be moved in project 1, but maybe the pegs will move in project 2?
     */
    @Override
    public void move(double dx, double dy){
        return;
    }
}
