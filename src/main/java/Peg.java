import bagel.*;
import bagel.util.*;

public class Peg extends Entity {

    /**
     * Screen Size constants. Pegs have a narrower window than the whole screen.
     */
    public static final double PEG_X_MIN = 0;
    public static final double PEG_X_MAX = 1024;
    public static final double PEG_Y_MIN = 100;
    public static final double PEG_Y_MAX = 768;

    private static final String imgPath = "res/peg.png";

    /**
     * Constructor for pegs
     */
    public Peg() {
        double x = utilities.randomInRange(getXMin(), getXMax());
        double y = utilities.randomInRange(getYMin(), getYMax());
        setPoint(x,y);
    }

    /**
     * Pegs have their own screen boundaries that other entities might not have: Need to override setters
     */
    public static double getXMin() {
        return PEG_X_MIN;
    }

    public static double getXMax() {
        return PEG_X_MAX;
    }

    public static double getYMin() {
        return PEG_Y_MIN;
    }

    public static double getYMax() {
        return PEG_Y_MAX;
    }

    /**
     *  Can't be moved in project 1, but maybe the pegs will move in project 2?
     */
    @Override
    public void moveBy(double dx, double dy){
        return;
    }
}
