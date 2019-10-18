import bagel.*;
import bagel.util.*;

/** Class to hold Blue Pegs */
public class BluePeg extends Peg {

    /** image path constants, depends on the type of image to load */
    public static final String NORMAL_IMG_PATH = "res/peg.png";
    public static final String HORIZ_IMG_PATH = "res/horizontal-peg.png";
    public static final String VERT_IMG_PATH = "res/vertical-peg.png";

    /** Peg Names: These are they names of pegs as read in by the CSV board files */
    public static final String BLUE_NAME       = "blue_peg";
    public static final String BLUE_HORIZ_NAME = "blue_peg_horizontal";
    public static final String BLUE_VERT_NAME  = "blue_peg_vertical";

    /** Standard Blue Peg constructor, used when taking in the type of the peg from the csv file
     * @param type - the name of the BluePeg
     * @param x - x coord
     * @param y - y coord
     */
    public BluePeg(String type, double x, double y) {
        super(filePathFromType(type), x, y);
    }

    /** generate a Blue Peg, used for converting between peg types
     * @param orientation - orientation of the peg
     * @param position - coordinates
     */
    public BluePeg(String orientation, Vector2 position) {
        super(filePathFromOrientation(orientation), position.x, position.y);
    }

    /** convert a BluePeg into a RedPeg
     * @return a red peg in the blue position's old spot. The Blue peg needs to be deleted externally!
     */
    public RedPeg transformRed(){
        return new RedPeg(orientation, getPosition());
    }

    /** convert a BluePeg into a GreenPeg
     * @return a green peg in the blue position's old spot. The Blue peg needs to be deleted externally!
     */
    public GreenPeg transformGreen(){
        return new GreenPeg(orientation, getPosition());
    }


    private static String filePathFromOrientation(String orientation){
        if (orientation.equals(Peg.ORIENTATION_NORMAL)) {
            return NORMAL_IMG_PATH;
        } else if (orientation.equals(Peg.ORIENTATION_HORIZ)) {
            return HORIZ_IMG_PATH;
        } else if (orientation.equals(Peg.ORIENTATION_VERT)) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Orientation: %s\033[0m\n", orientation);
            return "";
        }
    }

    private static String filePathFromType(String type) {
        if (type.equals(BLUE_NAME)) {
            return NORMAL_IMG_PATH;
        } else if (type.equals(BLUE_HORIZ_NAME)) {
            return HORIZ_IMG_PATH;
        } else if (type.equals(BLUE_VERT_NAME)) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Type: %s\033[0m\n", type);
            return "";
        }
    }
}
