import bagel.*;
import bagel.util.*;

public class Ball2 extends Entity {
    public static final double BALL_IMG_SIZE = 24;
    private static final String imgPath = "res/ball.png";
    public static final double DEFAULT_X = 512;
    public static final double DEFAULT_Y = 32;

    public static final double initialVelocity = 10f;
    public static final double gravityAcceleration = -0.15f;

    public Vector2 position;
    public Vector2 velocity;

    private boolean isOnScreen;

    /**
     * Constructor
     */
    public Ball2(Vector2 velocityVector) {
        image = new Image(imgPath);

        setPoint(DEFAULT_X, DEFAULT_Y);
        position = new Vector2(DEFAULT_X, DEFAULT_Y);
        rectangle = new Rectangle(getPoint(), BALL_IMG_SIZE, BALL_IMG_SIZE);

        /* shouldn't render the ball until there is a left click */
        isOnScreen = false;

        /* get the initial velocity components via the direction of the mouse click */
        velocity = new Vector2(velocityVector.x, velocityVector.y);
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
    public void moveBy(Vector2 change){
        double newX = getX();
        double newY = getY();

        if (isValidPos(getX() + change.x, getY() + change.y)){
            newX += change.x;
            newY += change.y;
            position.add(change);
        }

        /* if we would have passed over the edge, reverse out velocity */
        if (change.x + getX() <= getXMin() || change.x + getX() >= getXMax()) {
            velocity = new Vector2(-velocity.x, velocity.y);
        }



        setPoint(newX, newY);

        rectangle.moveTo(getPoint());

        if (newY + change.y > getYMax()) {
            isOnScreen = false;
        }
    }

    @Override
    public void moveBy(double x, double y) { return ;}

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

    public boolean isValidPos(double x, double y){
        if (x >= getXMin() && x <= getXMax() && y >= getYMin() && y <= getYMax()){
            return true;
        }
        return false;
    }
}


