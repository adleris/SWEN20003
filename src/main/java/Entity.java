import bagel.*;
import bagel.util.*;

public abstract class Entity {
    public Image image;
    private Point point;

    public abstract void move(double dx, double dy);

    public Entity() {
        // instantiate a temporary point
        point = new Point();
    }

    public void draw() {
        image.draw(getX(), getY());
    }

    /**
     * Getter to return point
     * @return
     */
    public Point getPoint() {
        return new Point(point.x, point.y);
    }

    /**
     * Getter to return x coordinate
     * @return
     */
    public double getX() {
        return point.x;
    }

    /**
     * Getter to return y coordinate
     * @return
     */
    public double getY() {
        return point.y;
    }

    /**
     * Setter for the Entity's point. Takes in a Point
     * @param point
     */
    public void setPoint(Point point) {
        if (point.x >= ShadowBounce.SCREEN_X_MIN && point.x <= ShadowBounce.SCREEN_X_MAX
                && point.y >= ShadowBounce.SCREEN_Y_MIN && point.y <= ShadowBounce.SCREEN_Y_MAX) {
            this.point = point;
        }
    }

    /**
     * Setter for the Entity's point. Takes in coordinate pair
     * @param x
     * @param y
     */
    public void setPoint(double x, double y) {
        System.out.format("Im trying to set a point @ (%.1f, %.1f)\n",x,y);
        if (x >= ShadowBounce.SCREEN_X_MIN && x <= ShadowBounce.SCREEN_X_MAX
                && y >= 0 && y <= ShadowBounce.SCREEN_Y_MAX) {
            this.point = new Point(x,y);
        }
    }

    /**
     * Setter for the Entity's x coordinate
     */
    public void setX(double x) {
        if (x >= ShadowBounce.SCREEN_X_MIN && x <= ShadowBounce.SCREEN_X_MAX) {
            this.point = new Point(x, this.point.y);
        }
    }

    /**
     * Setter for the Entity's y coordinate
     */
    public void setY(double y) {
        System.out.format("in set y (=%.2f)\n", y);
        System.out.format("Boundaries: %.2f to %.2f",ShadowBounce.SCREEN_Y_MIN,  ShadowBounce.SCREEN_Y_MAX);
        if (y >= 0 && y <= ShadowBounce.SCREEN_Y_MAX) {
            this.point = new Point(this.point.x, y);
        }
    }

}