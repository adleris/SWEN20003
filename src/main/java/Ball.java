import bagel.*;
import bagel.util.*;

public class Ball extends Entity {
    public static final double BALL_IMG_SIZE = 24;
    private static final String imgPath = "res/ball.png";
    public static final double DEFAULT_X = 512;
    public static final double DEFAULT_Y = 32;

    public static final double initialVelocity = 10f;
    public static final double gravityAcceleration = -0.15f;

    /* the ball's current velocity */
    public Vector2 velocity;

    /* determines if the ball is to rendered */
    private boolean isOnScreen;

    /**
     * Constructor
     */
    public Ball(Vector2 velocityVector) {
        image = new Image(imgPath);

        setPosition(DEFAULT_X, DEFAULT_Y);
        rectangle = new Rectangle(getPoint(), BALL_IMG_SIZE, BALL_IMG_SIZE);

        /* shouldn't render the ball until there is a left click */
        isOnScreen = false;

        /* get the initial velocity components via the direction of the mouse click */
        velocity = new Vector2(velocityVector.x, velocityVector.y);
    }

    /**
     * Attempt to move the ball by a (dx, dy) vector
     *
     * @param change
     */
    @Override
    public void moveBy(Vector2 change) {

        if (isValidPosition(getPosition().add(change))) {
            setPosition(getPosition().add(change));
        }

        /* if we would have passed over the edge, reverse x velocity */
        if (change.x + getX() <= getXMin() || change.x + getX() >= getXMax()) {
            velocity = new Vector2(-velocity.x, velocity.y);
        }

        rectangle.moveTo(getPoint());

        /* If the ball is off the screen delete it */
        if (getY() + change.y > getYMax()) {
            isOnScreen = false;
        }
    }

    /**
     * See if the ball is on screen and should be rendered
     *
     * @return
     */
    public boolean isOnScreen() {
        return isOnScreen;
    }

    /**
     * See ball to be on screen, doesn't allow hiding the ball
     * Hiding the ball is done internally in moveBy()
     *
     * @return
     */
    public void setOnScreen(boolean val) {
        if (val) {
            isOnScreen = true;
        }
    }
}

