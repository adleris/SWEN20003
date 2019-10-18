import bagel.*;
import bagel.util.*;
import java.util.Random;

/** Abstract class to store information on Pegs */
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
     * @param imagePath the location of the image on disk
     * @param x x coordinate
     * @param y y coordinate
     */
    public Peg(String imagePath, double x, double y) {
        super(imagePath, x, y);
        orientation = orientationFromFileName(imagePath);

        /* initially all Pegs aren't destroyed */
        isDestroyed = false;
    }

    /** Super Class method for Peg collision. Reverses the velocity of the Ball based on the side it hit from
     * @param ball  The colliding ball
     */
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
     * @return if the ball is destroyed
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


    /** @return the minimum x value */
    public static double getXMin() {
        return PEG_X_MIN;
    }

    /** @return the maximum x value */
    public static double getXMax() {
        return PEG_X_MAX;
    }

    /** @return the minimum y value */
    public static double getYMin() {
        return PEG_Y_MIN;
    }

    /** @return the maximum y value */
    public static double getYMax() {
        return PEG_Y_MAX;
    }

    /**
     * find a Peg's orientation based on its filename.
     * To add more orientations (eg diagonal), add more class constants.
     * Orientations pull from filenames so if the file names change some new constants will need to be added
     * @param fileName the filename that the peg will use
     * @return  the orientation of the peg
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
     * @param min the lower bound
     * @param max the upper bound
     * @return random double in the range
     */
    public static double randomInRange(double min, double max) {
        Random r = new Random();

        return r.nextDouble() * (max - min) + min;
    }
}