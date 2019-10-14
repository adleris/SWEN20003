import bagel.*;
import bagel.util.*;

public class RedPeg extends Peg {

    /** image path constants, depends on the type of image to load */
    public static final String NORMAL_IMG_PATH = "res/red-peg.png";
    public static final String HORIZ_IMG_PATH = "res/red-horizontal-peg.png";
    public static final String VERT_IMG_PATH = "res/red-vertical-peg.png";

    /** the number of red pegs in the game. when this drops to 0, the game ends
     * It's initially zero at the start of the game so that we can still do a check to end the game */
    private static int numRedPegs = -1;

    /**
     * constuctor from peg name and coordinates
     * @param type
     * @param x
     * @param y
     */
    public RedPeg(String type, double x, double y) {
        super(filePathFromType(type), x, y);
        if (numRedPegs == -1) {
            numRedPegs = 1;
        } else {
            numRedPegs++;
        }
    }

    /**
     * Constructor based on the orientation and position (used for converting between peg types)
     * @param orientation
     * @param position
     */
    public RedPeg(String orientation, Vector2 position){
        super(filePathFromOrientation(orientation), position.x, position.y);
        if (numRedPegs == -1) {
            numRedPegs = 1;
        } else {
            numRedPegs++;
        }
    }

    /** Determines if the game should end based on the number of Red Pegs running out */
    public static boolean shouldEndGame(){
        if (numRedPegs != 0) return false;
        return true;
    }

    //@override
    private static String filePathFromOrientation(String orientation){
        if (orientation == Peg.ORIENTATION_NORMAL) {
            return NORMAL_IMG_PATH;
        } else if (orientation == Peg.ORIENTATION_HORIZ) {
            return HORIZ_IMG_PATH;
        } else if (orientation == Peg.ORIENTATION_VERT) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Orientation: %s\033[0m\n", orientation);
            //throw new InvalidPegTypeException(orientation);
            return "";
        }
    }

    //@override
    private static String filePathFromType(String type) {
        if (type == Peg.RED_NAME) {
            return NORMAL_IMG_PATH;
        } else if (type == Peg.RED_HORIZ_NAME) {
            return HORIZ_IMG_PATH;
        } else if (type == Peg.RED_VERT_NAME) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Type: %s\033[0m\n", type);
            //throw new InvalidPegTypeException(type);
            return "";
        }
    }
}