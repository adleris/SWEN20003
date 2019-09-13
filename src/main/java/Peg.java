import bagel.*;
import bagel.util.*;

import java.util.Random;

public class Peg extends Entity {

    /**
     * Screen Size constants. Pegs have a narrower window than the whole screen.
     */
    public static final double PEG_X_MIN = 0;
    public static final double PEG_X_MAX = 1024;
    public static final double PEG_Y_MIN = 100;
    public static final double PEG_Y_MAX = 768;

    public static final double PEG_IMG_SIZE = 32;
    private static final String imgPath = "res/peg.png";

    private boolean isDestroyed;

    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * set a peg to be destroyed. Not a setter as it can't be 'undestroyed'
     */
    public void destroy() {
        isDestroyed = true;
    }

    /**
     * Constructor for pegs
     */
    public Peg() {
        image = new Image(imgPath);
        isDestroyed = false;

        double x = randomInRange(getXMin(), getXMax());
        double y = randomInRange(getYMin(), getYMax());

        setPosition(new Vector2(x,y));
        //rectangle = new Rectangle(getPoint(), PEG_IMG_SIZE, PEG_IMG_SIZE);
        rectangle = image.getBoundingBoxAt(getPoint());
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
    public void moveBy(Vector2 change){
        return;
    }

    /**
     * Generate a random double in the range [min, max]
     * @param min
     * @param max
     * @return
     */
    public static double randomInRange(double min, double max){
        Random r = new Random();

        return r.nextDouble() * (max-min) + min;
    }
}
