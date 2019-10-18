import bagel.*;
import bagel.util.*;
import java.util.Random;

public abstract class Peg extends Entity {

    /**
     * Screen Size constants. Pegs have a narrower window than the whole screen.
     * Accessed using getters, not directly
     */
    private static final double PEG_X_MIN = 0;
    private static final double PEG_X_MAX = 1024;
    private static final double PEG_Y_MIN = 100;
    private static final double PEG_Y_MAX = 768;

    /** strings to store information about the orientatin of a particular peg */
    public static final String ORIENTATION_NORMAL = "normal";
    public static final String ORIENTATION_HORIZ  = "horizontal";
    public static final String ORIENTATION_VERT   = "vertical";

    /** tells us the orientation of the peg, used in initialising and converting one type of peg to another */
    public final String orientation;

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
        orientation = null;     // for compatibility with project 1
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
        orientation = orientationFromFileName(imagePath);

        /* initially all Pegs aren't destroyed */
        isDestroyed = false;
    }

    /** Super Class method for Peg collision. Reverses the velocity of the Ball based on the side it hit from */
    public void collideWith(Ball ball) {
        Side side = getRectangle().intersectedAt(ball.getPoint(), ball.getVelocity());
        Vector2 newVelocity = null;
        if (side == Side.NONE) {
            return;
        } else if (side == Side.BOTTOM || side == Side.TOP) {
            newVelocity = new Vector2(ball.getVelocity().x, - ball.getVelocity().y);
        } else if (side == Side.LEFT || side == Side.RIGHT) {
            newVelocity = new Vector2(-ball.getVelocity().x, - ball.getVelocity().y);
        }
        ball.setVelocity(newVelocity);
        setIsDestroyed(true);
    }


    /**
     * See if a given peg has been destroyed
     * 
     * @return
     */
    public boolean getIsDestroyed() {
        return isDestroyed;
    }
    
    /**
     * set a peg to be destroyed. If destroyed, it will be removed from the game
     * @param isDestroyed The value to set it to
     */
    public void setIsDestroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
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
     * find a Peg's orientation based on its filename.
     * To add more orientations (eg diagonal), add more class constants.
     * Orientations pull from filenames so if the file names change some new constants will need to be added
     * @param fileName
     */
    public static String orientationFromFileName(String fileName){
        if (fileName.contains(ORIENTATION_HORIZ)){
            return ORIENTATION_HORIZ;
        } else if (fileName.contains(ORIENTATION_VERT)){
            return ORIENTATION_VERT;
        } else {
            return ORIENTATION_NORMAL;
        }
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