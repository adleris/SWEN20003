import bagel.*;
import bagel.util.*;
import java.lang.Math;

/** The class to hold the Game */
public class Bounce extends AbstractGame {

    private Board board;
    private int boardNum;
    
    /**
     * Game constructor
     */
    public Bounce() {
        boardNum = 0;
        board = new Board(boardNum);
    }

    /**
     * Start the show
     * @param args command line args
     */
    public static void main(String args[]) {
        Bounce game = new Bounce();
        game.run();
    }

    /**
     * Game loop: Update the ball's velocity and run collision checks. Initialises
     * the ball if need be.
     * @param input user input
     */
    @Override
    public void update(Input input) {

        /* let the board update other moving entities and check collisions
         * Here, we parse the input from user so Board doesn't need to.
         */
        if (input.isDown(MouseButtons.LEFT)) {
            Vector2 mousePos = input.getMousePosition().asVector();
            board.update(velocityFromMouse(mousePos));
        } else {
            board.update(null);
        }

        // test to advance Boards
        if (input.isDown(MouseButtons.RIGHT) || board.shouldEndBoard()) {
            try{Thread.sleep(100);} catch (Exception e) {;}   // update is way too fast, need time to depress key
            boardNum++;
            if (boardNum > 4) {
                Window.close();
            } else {
                board = new Board(boardNum);
            }
        }

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

    }

    /**
     * Find the initial velocity of the ball from the mouse coordinates
     *
     * @param mouse the vector of the mouse input
     * @return the velocity vector of the ball to that mouse
     */
    public static Vector2 velocityFromMouse(Vector2 mouse) {
        double distance = Math.sqrt((mouse.x - Ball.DEFAULT_X) * (mouse.x - Ball.DEFAULT_X)
                + (mouse.y - Ball.DEFAULT_Y) * (mouse.y - Ball.DEFAULT_Y));
        double velX = (mouse.x - Ball.DEFAULT_X) * Ball.initialVelocity / distance;
        double velY = (mouse.y - Ball.DEFAULT_Y) * Ball.initialVelocity / distance;
        return new Vector2(velX, velY);
    }

}