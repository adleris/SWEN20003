import bagel.*;
import bagel.util.*;

public class BluePeg extends Peg {

    /** image path constants, depends on the type of image to load */
    public static final String NORMAL_IMG_PATH = "res/peg.png";
    public static final String HORIZ_IMG_PATH = "res/horizontal-peg.png";
    public static final String VERT_IMG_PATH = "res/vertical-peg.png";

    public BluePeg(String type, double x, double y) {
        super(getFilePath(type), x, y);
    }


    // todo: Should both of these instead be Peg or Board methods?

    /** convert a BluePeg into a RedPeg
     * @return
     */
    public RedPeg transformRed(){
        return RedPeg(this);
    }

    /** convert a BluePeg into a GreenPeg
     * @return
     */
    public GreenPeg transformGreen(){
        return GreenPeg(this);
    }

    private static String getFilePath(String type) {
        if (type == Peg.BLUE_NAME) {
            return NORMAL_IMG_PATH;
        } else if (type == Peg.BLUE_HORIZ_NAME) {
            return HORIZ_IMG_PATH;
        } else if (type == Peg.BLUE_VERT_NAME) {
            return VERT_IMG_PATH;
        } else {
            System.out.format("\033[31mInvlaid Peg Type: %s\033[0m\n", type);
            //throw new InvalidPegTypeException(type);
            return "";
        }
    }
}
