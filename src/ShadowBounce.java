import bagel.*;
import java.util.Random;

public class ShadowBounce extends AbstractGame {

    public static final double SCREEN_X_MIN = 0;
    public static final double SCREEN_X_MAX = 1024;
    public static final double SCREEN_Y_MIN = 100;
    public static final double SCREEN_Y_MAX = 768;


    private Image plane;
    private Image bg;

    public Random random;



    private double x = 100;
    private double y = 100;

    /**
     * Game constructor
     */
    public ShadowBounce() {
        random = new Random();

        bg = new Image("res/land.jpeg");
        plane = new Image("res/plane.png");

        x = Window.getWidth() / 2;
        y = Window.getHeight() / 2;

        balloonX = random.nextDouble() * Window.getWidth();
        balloonY = random.nextDouble() * Window.getHeight();
    }

    /**
     * The entry point for the program.
     */
    public static void main(String args[]) {
        ShadowBounce game = new ShadowBounce();
        game.run();
    }


    /**
     * Performs a state update. This simple example shows an image that can be controlled with the arrow keys, and
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {
        double speed = 6f;
        if (input.isDown(Keys.LEFT) && (x - speed >= 0)) {
            x -= speed;
        }
        if (input.isDown(Keys.RIGHT) && (x + speed <= Window.getWidth())) {
            x += speed;
        }
        if (input.isDown(Keys.UP) && (y - speed > 0)) {
            y -= speed;
        }
        if (input.isDown(Keys.DOWN) && (y + speed <= Window.getHeight())) {
            y += speed;
        }

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        if (distanceFromToSquared(x, y, balloonX, balloonY) <= 50.0 * 50.0) {
            balloonX = random.nextDouble() * Window.getWidth();
            balloonY = random.nextDouble() * Window.getHeight();
        }

        bg.draw(Window.getWidth() / 2, Window.getHeight() / 2);
        plane.draw(x, y);
        balloon.draw(balloonX, balloonY);
    }

    private double distanceFromToSquared(double x1, double y1, double x2, double y2) {
        /**
         * return the distance between two points squared
         */
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}