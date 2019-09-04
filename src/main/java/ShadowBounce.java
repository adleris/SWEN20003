import bagel.*;
import bagel.util.*;

public class ShadowBounce extends AbstractGame {

    public static final int NUM_PEGS = 10;

    public Peg[] pegs;
    public Ball ball;

    public Peg demo;

    //private static final String backgroundFile = "";
    //private Image background;

    /**
     * Game constructor
     */
    public ShadowBounce() {
        ball = new Ball();
        //pegs = new Peg[1];
        //pegs[0] = new Peg();
        demo = new Peg();
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
        if (input.isDown(Keys.LEFT) && (ball.getX() - speed >= 0)) {
            //ball.setX(ball.getX() - speed);
            ball.moveBy( -speed,0);
        }
        if (input.isDown(Keys.RIGHT) && (ball.getX() + speed <= Window.getWidth())) {
           // ball.setX(ball.getX() + speed);
            ball.moveBy(speed, 0);
        }
        if (input.isDown(Keys.UP) && (ball.getY() - speed >= 0)) {
            //ball.setY(ball.getY() - speed);
            ball.moveBy(0, -speed);
        }
        if (input.isDown(Keys.DOWN) && (ball.getY() + speed <= Window.getHeight())) {
            //ball.setY(ball.getY() + speed);
            ball.moveBy(0,speed);
        }

        /* The ball then needs to fall under the influence of gravity */
        //ball.moveBy(0,(speed-1));

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

//        if (distanceFromToSquared(x, y, balloonX, balloonY) <= 50.0 * 50.0) {
//            balloonX = random.nextDouble() * Window.getWidth();
//            balloonY = random.nextDouble() * Window.getHeight();
//        }
//
//        bg.draw(Window.getWidth() / 2, Window.getHeight() / 2);
//        plane.draw(x, y);
//        balloon.draw(balloonX, balloonY);

        ball.draw();
        //pegs[0].draw();
        demo.draw();
    }

    private double distanceFromToSquared(double x1, double y1, double x2, double y2) {
        /**
         * return the distance between two points squared
         */
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }

}