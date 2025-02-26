import bagel.*;
import bagel.util.*;

/** Class to hold the powerup
 * Note that the fireball seems to be bugged and if a red bal is hit by the radial destroy of the fireball the game
 * might not advance to the next stage*/
public class PowerUp extends MovingEntity {
    private static final String imgPath = "res/powerup.png";

    /* powerup has its own minimum value */
    private static final double POWER_UP_Y_MIN = 0;

    /** the radius to destroy pegs in */
    public static final double DESTROY_RADIUS = 70f;

    /** 1/chance that a powerup spawns at the start of a turn */
    public static final int CREATION_CHANCE = 10;

    /** The magnitude of the velocity of the PowerUp */
    private static final double VELOCITY_MAGNITUDE = 3f;

    /** The distance away from destination where the power up will pick a new spot to go to. */
    private static final double DIST_TO_DEST = 5f;

    /* the destination the the point travels to after being initialised */
    private Vector2 destination;


    /**
     * Constructor for a PowerUp. Generates a random initial coordinate
     */
    public PowerUp() {
        /* Set up the powerup at random coordinates */
        super(imgPath, randomPosition());

        /* pick a random destination */
        destination = randomPosition();

        /* figure out the velocity vector between the generated start position and destination */
        setVelocity(velocityFromSourceToDest(getPosition(), destination));
    }


    /**
     * Attempt to move the power-up by a (dx, dy) vector
     * @param change The change to move the power up by
     */
    @Override
    public void moveBy(Vector2 change) {
        /* figure out the distance to the destination */
        double distance = Math.sqrt((destination.x - getX()) * (destination.x - getX())
                + (destination.y - getY()) * (destination.y - getY()));

        /* if is valid position and distance > DIST_TO_DEST move; else get new coord */
        if (isValidPosition(getPosition().add(change)) && distance > DIST_TO_DEST) {
            setPosition(getPosition().add(change));
            /* move the image to the current position */
            setRectangle(getImage().getBoundingBoxAt(getPoint()));
        } else if (distance <= DIST_TO_DEST) {
            destination = randomPosition();
            setVelocity(velocityFromSourceToDest(getPosition(), destination));
        }


    }

    /** Get a random Vector2 position within the bound sof the game window
     * @return a random coordinate position that's on the board
     */
    private static Vector2 randomPosition() {
        return new Vector2(Peg.randomInRange(getXMin(), getXMax()), Peg.randomInRange(POWER_UP_Y_MIN, getYMax()));
    }


    /**
     * Find the velocity of the powerup based on current position and destination
     * @param source source position
     * @param dest   destination position
     * @return       vector2 with the velocity needed between the points
     */
    private static Vector2 velocityFromSourceToDest(Vector2 source, Vector2 dest) {
        double distance = Math.sqrt((dest.x - source.x) * (dest.x - source.x)
                + (dest.y - source.y) * (dest.y - source.y));
        double velX = (dest.x - source.x) * VELOCITY_MAGNITUDE / distance;
        double velY = (dest.y - source.y) * VELOCITY_MAGNITUDE / distance;
        return new Vector2(velX, velY);
    }
}
