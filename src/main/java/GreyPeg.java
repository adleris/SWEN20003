import bagel.*;
import bagel.util.*;

/** Class to hold the Grey Peg */
public class GreyPeg extends Peg {

    /** image path constants, depends on the type of image to load */
    private static final String NORMAL_IMG_PATH = "res/grey-peg.png";
    private static final String HORIZ_IMG_PATH = "res/grey-horizontal-peg.png";
    private static final String VERT_IMG_PATH = "res/grey-vertical-peg.png";

    /** Peg Names: These are they names of pegs as read in by the CSV board files */
    private static final String PEG_NAME       = "grey_peg";
    private static final String PEG_HORIZ_NAME = "grey_peg_horizontal";
    private static final String PEG_VERT_NAME  = "grey_peg_vertical";

    /** Standard Grey Peg constructor, used when taking in the type of the peg from the csv file
     * @param type - the name of the GreyPeg
     * @param x - x coord
     * @param y - y coord
     */
    public GreyPeg(String type, double x, double y) {
        super(filePathFromType(type), x, y);
    }

    /** Setter for the Grey peg's is destroyed, does nothing but works for compatibility
     *
     * @param value any boolean value
     */
    @Override
    public void setIsDestroyed(boolean value){
        super.setIsDestroyed(false);
    }

    /** Have a Grey Peg collide with a Ball. 'Undestroys' the peg
     * @param ball The ball that is colliding with the peg
     */
    @Override
    public void collideWith(Ball ball){
        super.collideWith(ball);
        setIsDestroyed(false);
    }

    /** return the file path from the name of the peg. Should be overriden from Peg but the method must be static and
     * Java won't let you have static abstract
     * @param type the name of the peg
     * @return the file path
     */
    private static String filePathFromType(String type) {
        if (type.equals(PEG_NAME)) {
            return NORMAL_IMG_PATH;
        } else if (type.equals(PEG_HORIZ_NAME)) {
            return HORIZ_IMG_PATH;
        } else if (type.equals(PEG_VERT_NAME)) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Type: %s\033[0m\n", type);
            return "";
        }
    }
}
