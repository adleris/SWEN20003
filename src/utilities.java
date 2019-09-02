import java.util.Random;
public class utilities {
    //public Random random;

    public static double randomInRange(double min, double max){
        Random r = new Random();

        return r.nextDouble() * (max-min) + min;
    }

    public static int randomInRange(int min, int max){
        Random r = new Random();

        return r.nextInt() * (max-min) + min;
    }

    public static void main(String args[]){
        for (int i = 0; i < 10; i++){
            System.out.println(randomInRange(0,1024));
        }
    }
}
