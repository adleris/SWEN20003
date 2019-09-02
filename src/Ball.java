import bagel.*;
import bagel.util.*;

public class Ball extends Entity {
    private static final String imgPath = "res/ball.jpg";
    private static final double DEFAULT_X = 512;
    private static final double DEFAULT_Y = 32;

    /**
     * Constructor for the case where we start at the default point
     */
    public Ball() {
        image = new Image(imgPath);
        setPoint(DEFAULT_X, DEFAULT_Y);
    }

    /**
     * Constructor in case the point we start at changes in project 2
     */
    public Ball(double x, double y) {
        image = new Image(imgPath);
        setPoint(x, y);
    }

    /**
     * Attempt to move the ball by (dx, dy). Returns a valid position. If the move would have taken the point off of 
     * the screen, it returns the original coordinate in that direction
     * todo: fix it up so it leaves the point on the edge of the screen?
     * @param dx
     * @param dy
     */
    @Override
    public void move(double dx, double dy){
        double newX = getX();
        double newY = getY();

        if (getX() + dx >= ShadowBounce.SCREEN_X_MIN && getX() + dx <= ShadowBounce.SCREEN_X_MAX){
            newX += dx;
        }
        if (getY() + dy >= ShadowBounce.SCREEN_Y_MIN && getY() + dy <= ShadowBounce.SCREEN_Y_MAX){
            newY += dy;
        }

        setPoint(newX, newY);
    }

}
