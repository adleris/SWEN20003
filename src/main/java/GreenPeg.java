import bagel.*;
import bagel.util.*;
import java.lang.Math;

public class GreenPeg extends Peg {

    /** image path constants, depends on the type of image to load */
    public static final String NORMAL_IMG_PATH = "res/green-peg.png";
    public static final String HORIZ_IMG_PATH = "res/green-horizontal-peg.png";
    public static final String VERT_IMG_PATH = "res/green-vertical-peg.png";

    public static final double BALL_VELOCITY = 10f;
    public static final int N_BALLS_SUMMONED = 2;

    /** generate a Green Peg, used for converting between peg types
     * @param orientation - orientation of the peg
     * @param position - coordinates
     */
    public GreenPeg(String orientation, Vector2 position) {
        super(filePathFromOrientation(orientation), position.x, position.y);
    }

    /** convert a GreenPeg into a BluePeg
     * @return a Blue Peg
     */
    public BluePeg transformBlue(){
        return new BluePeg(orientation, getPosition());
    }

    /** Spawn new balls a green ball on collision
     * @param ball the ball that is intersecting with the green peg
     * @return 2 balls with perpendicular velocities coming out of the top of the green peg
     */
    public Ball[] spawnBlueBalls(Ball ball){
        /* we summon 2 balls at 45deg from horizontal. (cos(45) = 1/sqrt(2))*/
        double velocityComponent = BALL_VELOCITY/Math.sqrt(2);

        Ball[] newBalls = new Ball[N_BALLS_SUMMONED];
        /* set up each of the 2 balls with same y velocity but different x velocity */
        newBalls[0] = new Ball(getPosition(), new Vector2(velocityComponent, velocityComponent));
        newBalls[1] = new Ball(getPosition(), new Vector2(- velocityComponent, velocityComponent));
        return newBalls;
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
}
