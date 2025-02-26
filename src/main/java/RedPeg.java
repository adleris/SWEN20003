import bagel.*;
import bagel.util.*;

/** Class to hold red peg */
public class RedPeg extends Peg {

    /** image path constants, depends on the type of image to load */
    private static final String NORMAL_IMG_PATH = "res/red-peg.png";
    private static final String HORIZ_IMG_PATH = "res/red-horizontal-peg.png";
    private static final String VERT_IMG_PATH = "res/red-vertical-peg.png";

    /** Peg Names: These are they names of pegs as read in by the CSV board files */
    private static final String RED_NAME        = "red_peg";
    private static final String RED_HORIZ_NAME  = "red_peg_horizontal";
    private static final String RED_VERT_NAME   = "red_peg_vertical";

    /** The proportion of red pegs that should be created at the start of the game */
    public static final int PROPORTION_TO_RED = 5;

    /** the number of red pegs in the game. when this drops to 0, the game ends
     * It's initially zero at the start of the game so that we can still do a check to end the game */
    private static int numRedPegs = -1;

    /**
     * Constructor based on the orientation and position (used for converting between peg types)
     * @param orientation the orientation of the peg
     * @param position the coordinate
     */
    public RedPeg(String orientation, Vector2 position){
        super(filePathFromOrientation(orientation), position.x, position.y);
        if (numRedPegs == -1) {
            numRedPegs = 1;
        } else {
            numRedPegs++;
        }
    }

    /** Determines if the game should end based on the number of Red Pegs running out
     * @return if the game should end */
    public static boolean shouldEndGame(){
        if (numRedPegs != 0) return false;
        return true;
    }

    /** Collide a red peg with a ball. drops the red peg count
     *
     * @param ball  The colliding ball
     */
    @Override
    public void collideWith(Ball ball){
        super.collideWith(ball);
        numRedPegs--;
        setIsDestroyed(true);
    }

    /** reduce the number of red pegs by 1 */
    public static void reduceNumRedPegs(){
        numRedPegs--;
    }

    /** Get the number of red Pegs remaining
     * @return the number of red pegs remaining
     */
    public static int getNumRedPegs() {
        return numRedPegs;
    }

    /** set the numRedPegs value to be its uninitialised value */
    public static void resetNumRedPegs() {
        numRedPegs = -1;
    }


    /** return the file path from the given orientation. Should be overriden from Peg but the method must be static and
     * Java won't let you have static abstract
     * @param orientation the orientation of the peg
     * @return the file path
     */
    private static String filePathFromOrientation(String orientation){
        if (orientation.equals(Peg.ORIENTATION_NORMAL)) {
            return NORMAL_IMG_PATH;
        } else if (orientation.equals(Peg.ORIENTATION_HORIZ)) {
            return HORIZ_IMG_PATH;
        } else if (orientation.equals(Peg.ORIENTATION_VERT)) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Orientation: %s\033[0m\n", orientation);
            //throw new InvalidPegTypeException(orientation);
            return "";
        }
    }

}