import bagel.*;
import bagel.util.*;

public class Ball extends Entity {
    public static final double BALL_IMG_SIZE = 24;
    private static final String imgPath = "res/ball.png";
    public static final double DEFAULT_X = 512;
    public static final double DEFAULT_Y = 32;

    public static final double initialVelocity = 10f;
    public static final double gravityAcceleration = -0.15f;

    private boolean isOnScreen;

    /**
     * Constructor for the case where we start at the default point
     */
    public Ball() {
        image = new Image(imgPath);

        setPosition(DEFAULT_X, DEFAULT_Y);
        rectangle = new Rectangle(getPoint(), BALL_IMG_SIZE, BALL_IMG_SIZE);
        //System.out.println(this);

        /* shouldn't render the ball until there is a left click */
        isOnScreen = false;
    }

    /**
     * Constructor in case the point we start at changes in project 2
     */
    public Ball(double x, double y) {
        image = new Image(imgPath);
        setPosition(x, y);
    }

    public String toString(){
        return String.format("Ball at %f, %f", getX(), getY());
    }

    /**
     * Attempt to move the ball by (dx, dy)
     * todo: fix it up so it leaves the point on the edge of the screen?
     * @param dx
     * @param dy
     */
    @Override
    public void moveBy(double dx, double dy){
        double newX = getX();
        double newY = getY();

        if (getX() + dx >= getXMin() && getX() + dx <= getXMax()){
            newX += dx;
        }
        if (getY() + dy >= getYMin() && getY() + dy <= getYMax()){
            newY += dy;
        }

        setPosition(newX, newY);
        rectangle.moveTo(getPoint());

        if (newY + dy > getYMax()) {
            isOnScreen = false;
        }
    }

    /**
     * See if the ball is on screen and should be rendered
     * @return
     */
    public boolean isOnScreen() {
        return isOnScreen;
    }

    /**
     * See ball to be on screen, doesn't allow hiding the ball
     * Hiding the ball is done internally in moveBy()
     * @return
     */
    public void setOnScreen(boolean val) {
        if (val) {
            isOnScreen = val;
        }
    }
}

