import bagel.*;
import bagel.util.*;

public class Ball extends MovingEntity {

    private static final String imgPath = "res/ball.png";

    /* initial values to spawn the ball at */
    public static final double DEFAULT_X = 512;
    public static final double DEFAULT_Y = 32;

    public static final double initialVelocity = 10f;
    public static final double gravityAcceleration = 0.15f;

    /* determines if the ball is to rendered */
    private boolean isOnScreen;

    /* determines if a ball should interact as a fireball */
    private boolean isFireBall;

    /**
     * Constructor for a ball
     */
    public Ball(Vector2 velocityVector) {
        /* Set up the Entity at the default coordinates */
        super(imgPath, DEFAULT_X, DEFAULT_Y);

        /* get the initial velocity components via the velocity vector */
        //velocity = new Vector2(velocityVector.x, velocityVector.y);   // old code
        setVelocity(new Vector2(velocityVector.x, velocityVector.y));
        /* shouldn't render the ball until there is a left click */
        isOnScreen = false;
    }

    /**
     * Constructor for a ball that takes in the initial coordinate for the ball to spawn at and its type
     */
    public Ball(Vector2 position, Vector2 velocityVector, boolean isFireBall) {
        /* Set up the Entity at the default coordinates */
        super(imgPath, position.x, position.y);

        this.isFireBall = isFireBall;
        /* get the initial velocity components via the velocity vector */
        setVelocity(new Vector2(velocityVector.x, velocityVector.y));
        /* a ball set up in this fashion should already be on the screen */
        isOnScreen = true;
    }

    /**
     * Attempt to move the ball by a (dx, dy) vector
     *
     * @param change
     */
    @Override
    public void moveBy(Vector2 change) {

        /* If the move is valid, move */
        if (isValidPosition(getPosition().add(change))) {
            setPosition(getPosition().add(change));
            /* move the image to the current position */
            setRectangle(getImage().getBoundingBoxAt(getPoint()));
        }

        /* if we would have passed over the edge, reverse x velocity */
        if (change.x + getX() <= getXMin() || change.x + getX() >= getXMax()) {
            //velocity = new Vector2(-velocity.x, velocity.y);          // old code
            setVelocity(new Vector2( - getVelocity().x, getVelocity().y));
        }

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
     * Determines if a ball should interact as a fireball or just as a regular ball
     * @return
     */
    public boolean isFireBall() {
        return isFireBall;
    }

    /**
     * See ball to be on screen, doesn't allow hiding the ball Hiding the ball is
     * done internally in moveBy()
     *
     * @return
     */
    public void setOnScreen(boolean val) {
        if (val) {
            isOnScreen = true;
        }
    }
}
