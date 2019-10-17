import bagel.*;
import bagel.util.*;
import java.lang.Math;
import java.util.ArrayList;


public class Bounce extends AbstractGame {

    private Board board;
    private ArrayList<Ball> balls;

    /**
     * Game constructor
     */
    public Bounce() {
//        for (int i = 0; i < 1; i++) {
//            board = new Board(i);
//        }
        board = new Board(0);
        balls = new ArrayList<>();
        //balls.add(new Ball(new Vector2(0, 0)));
    }

    /**
     * Start the show
     */
    public static void main(String args[]) {
        Bounce game = new Bounce();
        game.run();
    }

    /**
     * Game loop: Update the ball's velocity and run collision checks. Initialises
     * the ball if need be.
     */
    @Override
    public void update(Input input) {
        ArrayList<Ball> ballsToRemove = new ArrayList<>();

        /* If there are no balls in the array list, see if we should make a new one */
        if (balls.size() == 0 && input.isDown(MouseButtons.LEFT)) {
            /* if a left click is made, make a new ball and add it to the arraylist */
            Vector2 mousePos = input.getMousePosition().asVector();
            Ball newBall = new Ball(velocityFromMouse(mousePos));
            newBall.setOnScreen(true);

            balls.add(newBall);
        }

        /* run through all active balls and move them, this does nothing if array list is empty (no balls) */
        for (Ball ball : balls) {
            /*
             * there are some balls on the screen: adjust their acceleration according to
             * gravity, then move them
             */
            ball.setVelocity(ball.getVelocity().add(new Vector2(0, Ball.gravityAcceleration)));
            ball.moveBy(ball.getVelocity());

            /* if this move sends the ball off of the screen, schedule ball to be removed from the ball array */
            if (! ball.isOnScreen()) {
                ballsToRemove.add(ball);
            }
        }

        /* remove any balls that left the screen */
        for (Ball ball : ballsToRemove) {
            balls.remove(ball);
        }



        /* let the board update other moving entities and check collisions */
        board.update(balls);

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
            board = new Board(1);
            balls = new ArrayList<>();
        }

    }

    /**
     * Find the initial velocity of the ball from the mouse coordinates
     *
     * @param mouse
     * @return
     */
    public static Vector2 velocityFromMouse(Vector2 mouse) {
        double distance = Math.sqrt((mouse.x - Ball.DEFAULT_X) * (mouse.x - Ball.DEFAULT_X)
                + (mouse.y - Ball.DEFAULT_Y) * (mouse.y - Ball.DEFAULT_Y));
        double velX = (mouse.x - Ball.DEFAULT_X) * Ball.initialVelocity / distance;
        double velY = (mouse.y - Ball.DEFAULT_Y) * Ball.initialVelocity / distance;
        return new Vector2(velX, velY);
    }

}