import bagel.*;
import bagel.util.*;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
    public static int INTIAL_SHOTS = 20;
    private int numShots;
    private int numBlue;

    public static String FILE_PATTERN = "#.csv";

    public ArrayList<Peg> pegs;
    public Bucket bucket;
    public Ball[] balls;
    public powerup powerup;

    /* constructor */
    public Board(int boardNumber){
        // set up the peg array
        pegs = new ArrayList<>();
        readInBoard(boardNumber);
        powerup = new PowerUp();
        bucket = new Bucket();
        balls = new Ball[2];             // double check that this is set at 2?
        balls[0] = new Ball();
    }

    // like the main method
    public void update(Input input) {
        // wait for a mouseclick

    }

    public void checkCollisions() {
        // the insides ofthis can be tidied up
        for (Ball ball : balls) {
            if (ball.isFireBall()) {
                // need to explode on intersect
                for (Peg peg : pegs) {
                    if (ball.intersects(peg)) {
                        if (! peg.getColour() == "grey") {
                            DestroyPegsInRadius(ball);
                        }
                        if (peg.getColour() == "Green"){
                            this.Balls = GreenPegSummonBall(ball);      // this aint right but that's ok for now
                            // if we hit a green peg there should have only been one ball
                        }
                    }
                }
            } else {
                for (Peg peg : pegs) {
                    // normal intersect handling
                    if (! peg.getColour() == "grey") {
                        peg.destroy();
                    }
                    if (peg.getColour() == "Green"){
                        this.Balls = GreenPegSummonBall(ball);      // this aint right but that's ok for now
                        // if we hit a green peg there should have only been one ball
                    }
                }
            }
        }

        // here, we will also check the intersections of the bucket and powerUp
    }

    public void renderScreen() {
        // check if we should render things

        // pegs
        for (Peg peg : pegs) {
            if (! peg.isDestroyed()){
                peg.draw();
            }
        }

        // then also do balls, powerup, bucket
    }

    public void readInBoard(int boardNumber) {
        String filename = FILE_PATTERN.replace("#", Integer.toString(boardNumber));


        // open up the file, iterate through it's lines and save them into our Peg array
        // set up the pegs array inside here as well with "new"

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String row;
            String[] columns;

            while ((row = br.readLine()) != null) {
                columns = row.split(",");
                // make sure we have a name, x, y
                if (columns.length != 3) {
                    throw new Exception();
                }
                // add a new peg with type based on its name, x, and y
                pegs.add(newPegFromName(columns[0], Double.parseDouble(columns[1]), Double.parseDouble(columns[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Peg newPegFromName(String type, double x, double y){
        if (type.contains("blue")) {
            return new BluePeg(type, x, y);
        }
        else if (type.contains("grey")) {
            return new Peg(type, x, y);
        }
    }


    public void DestroyPegsInRadius(Ball ball) {
        // make a box / circle (using euclidean distance) and then check intersections
        // with every peg using the intersect method
    }
    public BluePeg getRandomBlue() {
        // randomly choose a blue peg
        for (Peg peg : pegs){
            if (peg.colour == Peg.COLOUR_BLUE) {
                return new BluePeg(peg);
            }
        }
        return null;
    }
}







