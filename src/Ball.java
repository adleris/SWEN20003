import bagel.*;
import bagel.util.*;

public class Ball extends Entity {
    private final String imgPath = "res/ball.jpg";
    private final double DEFAULT_X = 512;
    private final double DEFAULT_Y = 32;

    /**
     * Constructor for the case where we start at the default point
     */
    public Ball() {
        this.image = new Image(imgPath);
        this.point = new Point(DEFAULT_X, DEFAULT_Y);
        
    }

    /**
     * Constructor in case the point we start at changes in project 2
     */
    public Ball(double x, double y) {
        this.image = new Image(imgPath);
        this.point = new Point(x, y);
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
        double newX = point.x;
        double newY = point.y;

        if (point.x + dx > ShadowBounce.SCREEN_X_MIN && point.x + dx < ShadowBounce.SCREEN_X_MAX){
            newX = point.x + dx;
        }
        if (point.y + dy > ShadowBounce.SCREEN_Y_MIN && point.y + dy < ShadowBounce.SCREEN_Y_MAXx){
            newY = point.y + dy;
        }

        point = new Point(newX, newY);
    }

}
