import bagel.*;
import bagel.util.*;
import java.util.Random;

public class Peg extends Entity {

    /**
     * Screen Size constants. Pegs have a narrower window than the whole screen.
     * Accessed using getters, not directly
     */
    private static final double PEG_X_MIN = 0;
    private static final double PEG_X_MAX = 1024;
    private static final double PEG_Y_MIN = 100;
    private static final double PEG_Y_MAX = 768;

    /** Peg Names: These are they names of pegs as read in by the CSV board files */
    public static final String BLUE_NAME       = "blue_peg";
    public static final String BLUE_HORIZ_NAME = "blue_peg_horizontal";
    public static final String BLUE_VERT_NAME  = "blue_peg_vertical";
    public static final String GREY_NAME       = "grey_peg";
    public static final String GREY_HORIZ_NAME = "grey_peg_horizontal";
    public static final String GREY_VERT_NAME  = "grey_peg_vertical";
    public static final String RED_NAME        = "red_peg";
    public static final String RED_HORIZ_NAME  = "red_peg_horizontal";
    public static final String RED_VERT_NAME   = "red_peg_vertical";

    // kept here for compatibility with project 1 so far, remove this later on
    private static final String imgPath = "res/peg.png";

    /* determines if the peg is to rendered */
    private boolean isDestroyed;

    /**
     * Constructor for pegs that randomly generates a Peg
     */
    public Peg() {
        /* set up an Entity with a random x and y coordinate */
        super(imgPath, randomInRange(getXMin(), getXMax()), randomInRange(getYMin(), getYMax()));

        /* initially all Pegs aren't destroyed */
        isDestroyed = false;
    }

    /**
     * Constructor that takes in coordinates and an image path
     * @param imagePath
     * @param x
     * @param y
     */
    public Peg(String imagePath, double x, double y) {
        super(imagePath, x, y);

        /* initially all Pegs aren't destroyed */
        isDestroyed = false;
    }

    /**
     * See if a given peg has been destroyed
     * 
     * @return
     */
    public boolean isDestroyed() {
        return isDestroyed;
    }

    /**
     * set a peg to be destroyed. Not a setter as it can never / should never be
     * 'undestroyed'
     */
    public void destroy() {
        isDestroyed = true;
    }

    // todo: move this into the individual peg subtypes, grey cant be destroyed.
    /**
     * Pegs have their own screen boundaries that other entities might not have:
     * Need to override setters
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
     * Generate a random double in the range [min, max]
     * 
     * @param min
     * @param max
     * @return
     */
    public static double randomInRange(double min, double max) {
        Random r = new Random();

        return r.nextDouble() * (max - min) + min;
    }
}


//todo: should Peg be made abstract? Then getFileName becomes an  overridden abstract method, as would Destroy or whatever I end up doing with it