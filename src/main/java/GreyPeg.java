import bagel.*;
import bagel.util.*;

public class GreyPeg extends Peg {

    /** image path constants, depends on the type of image to load */
    public static final String NORMAL_IMG_PATH = "res/grey-peg.png";
    public static final String HORIZ_IMG_PATH = "res/grey-horizontal-peg.png";
    public static final String VERT_IMG_PATH = "res/grey-vertical-peg.png";

    /** Peg Names: These are they names of pegs as read in by the CSV board files */
    public static final String GREY_NAME       = "grey_peg";
    public static final String GREY_HORIZ_NAME = "grey_peg_horizontal";
    public static final String GREY_VERT_NAME  = "grey_peg_vertical";

    /** Standard Grey Peg constructor, used when taking in the type of the peg from the csv file
     * @param type - the name of the GreyPeg
     * @param x - x coord
     * @param y - y coord
     */
    public GreyPeg(String type, double x, double y) {
        super(filePathFromOrientation(type), x, y);
    }

    /** generate a Grey Peg, used for converting between peg types
     * @param orientation - orientation of the peg
     * @param position - coordinates
     */
    public GreyPeg(String orientation, Vector2 position) {
        super(filePathFromOrientation(orientation), position.x, position.y);
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
        if (type == GREY_NAME) {
            return NORMAL_IMG_PATH;
        } else if (type == GREY_HORIZ_NAME) {
            return HORIZ_IMG_PATH;
        } else if (type == GREY_VERT_NAME) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Type: %s\033[0m\n", type);
            //throw new InvalidPegTypeException(type);
            return "";
        }
    }
}
