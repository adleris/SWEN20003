import bagel.*;
import bagel.util.*;

public class PowerUp extends MovingEntity {
    private static final String imgPath = "res/powerup.png";

    /** 1/chance that a powerup spawns at the start of a turn */
    public static final int CREATION_CHANCE = 10;

    /** The magnitude of the velocity of the PowerUp */
    public static final double VELOCITY_MAGNITUDE = 3f;

    /** The distance that the powerup will pick a new spot to go to */
    public static final double DIST_TO_DEST = 1f;

    /* the destination the the point travels to after being initialised */
    private Vector2 destination;

    /* determines if the powerup is to rendered */
    private boolean isOnScreen;


    /**
     * Constructor for a PowerUp. Generates a random initial coordinate
     */
    public PowerUp() {
        /* Set up the powerup at random coordinates */
        super(imgPath, randomPosition());

        /* pick a random destination */
        destination = randomPosition();

        /* figure out the velocity vector between the position and destination */


        /* get the initial velocity from the generated start point to the created destination */
        setVelocity(velocityFromSourceToDest(getPosition(), destination));
        /* if the powerup exists, it is on screen */
        isOnScreen = true;
    }


    //todo this is currently just the balls move and will run it off the screen
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

    /** Get a random Vector2 position within the bound sof the game window */
    private static Vector2 randomPosition() {
        return new Vector2(Peg.randomInRange(getXMin(), getXMax()), Peg.randomInRange(getYMin(), getYMax()));
    }


    /**
     * Find the velocity of the powerup based on current position and destination
     *
     * @param source source position
     * @param dest   destination position
     * @return       vector2 with the velocity needed between the points
     */
    public static Vector2 velocityFromSourceToDest(Vector2 source, Vector2 dest) {
        double distance = Math.sqrt((dest.x - source.x) * (dest.x - source.x)
                + (dest.y - source.y) * (dest.y - source.y));
        double velX = (dest.x - source.x) * VELOCITY_MAGNITUDE / distance;
        double velY = (dest.y - source.y) * VELOCITY_MAGNITUDE / distance;
        return new Vector2(velX, velY);
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
