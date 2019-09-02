import bagel.*;
import bagel.util.*;

public class Peg extends Entity {
    private static final String imgPath = "res/peg.png";

    /**
     * Constructor for pegs
     */
    public Peg() {
        double x = utilities.randomInRange(ShadowBounce.SCREEN_X_MIN, ShadowBounce.SCREEN_X_MAX);
        double y = utilities.randomInRange(ShadowBounce.SCREEN_Y_MIN, ShadowBounce.SCREEN_Y_MAX);
        setPoint(x,y);
    }

    /**
     *  Can't be moved in project 1, but maybe the pegs will move in project 2?
     */
    @Override
    public void moveBy(double dx, double dy){
        return;
    }
}
