import bagel.*;
import bagel.util.*;

public class Ball extends Entity {
    private static final String imgPath = "res/ball.png";
    private static final double DEFAULT_X = 512;
    private static final double DEFAULT_Y = 32;

    /**
     * Constructor for the case where we start at the default point
     */
    public Ball() {
        image = new Image(imgPath);

        setPoint(DEFAULT_X, DEFAULT_Y);
        System.out.println(this);
    }

    /**
     * Constructor in case the point we start at changes in project 2
     */
    public Ball(double x, double y) {
        image = new Image(imgPath);
        setPoint(x, y);
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

        if (getX() + dx >= 0 && getX() + dx <= Window.getWidth()){
            newX += dx;
        }
        if (getY() + dy >= 0 && getY() + dy <= Window.getHeight()){
            newY += dy;
        }

        setPoint(newX, newY);
    }

}
