import bagel.*;
import bagel.util.*;

public abstract class Entity {

    /**
     * Generic screen sizes we can store the entity in. A generic entity can be anywhere on the screen.
     */
    public static final double ENTITY_X_MIN = 0;
    public static final double ENTITY_X_MAX = Window.getWidth();
    public static final double ENTITY_Y_MIN = 0;
    public static final double ENTITY_Y_MAX = Window.getHeight();
    
    public Image image;
    private Point point;

    public abstract void moveBy(double dx, double dy);

    public Entity() {
        // instantiate a temporary point
        point = new Point();
    }

    /**
     * Draw an entity to the screen.
     */
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
     * Setter for the Entity's point. Only modifies if the new point is in the screen. Takes in a Point
     * @param point
     */
    public void setPoint(Point point) {
        if (point.x >= getXMin() && point.x <= getXMax()
                && point.y >= getYMin() && point.y <= getYMax()) {
            this.point = point;
        }
    }

    /**
     * Setter for the Entity's point. Only modifies if the new point is in the screen. Takes in coordinate pair
     * @param x
     * @param y
     */
    public void setPoint(double x, double y) {
        System.out.format("Im trying to set a point @ (%.1f, %.1f)\n",x,y);
        if (x >= getXMin() && x <= getXMax()
                && y >= getYMin() && y <= getYMax()) {
            this.point = new Point(x,y);
        }
    }

    /**
     * Setter for the Entity's x coordinate. Only modifies if the new point is in the screen.
     */
    public void setX(double x) {
        if (x >= getXMin() && x <= getXMax()) {
            this.point = new Point(x, this.point.y);
        }
    }

    /**
     * Setter for the Entity's y coordinate. Only modifies if the new point is in the screen.
     */
    public void setY(double y) {
        System.out.format("in set y (=%.2f)\n", y);
        System.out.format("Boundaries: %.2f to %.2f",getYMin(), getYMax());
        if (y >= getYMin() && y <= getYMax()) {
            this.point = new Point(this.point.x, y);
        }
    }

    /**
     * Need some other getters to account for the differing max values. This reduces duplicate code in eg. Peg class
     */

    public static double getXMin() {
        return ENTITY_X_MIN;
    }

    public static double getXMax() {
        return ENTITY_X_MAX;
    }

    public static double getYMin() {
        return ENTITY_Y_MIN;
    }

    public static double getYMax() {
        return ENTITY_Y_MAX;
    }

}