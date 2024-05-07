import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

<<<<<<< HEAD
=======
/**
 * Write a description of JavaFX class Paddle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// this class makes the paddles and configures them. it also lets them move

>>>>>>> 0694641e31b7e1cc6f6ffa6cd36ba3214d6aa0bf
public class Paddle {
    private static final int paddle_width = 15; // variables for the paddle
    private static final int paddle_height = 80;
    private Rectangle rectangle;
    // initializes paddle with starting position
    public Paddle(double startX, double startY) {
        rectangle = new Rectangle(paddle_width, paddle_height, Color.CRIMSON);
        rectangle.setX(startX);
        rectangle.setY(startY);
    }
    // getter for rectangle object
    public Rectangle getRectangle() {
        return rectangle;
    }
    // method for moving paddle
    public void move(int dy, int windowHeight) {
        double newY = rectangle.getY() + dy; // calculates y coordinate
        if (newY >= 0 && newY <= windowHeight - paddle_height) { //  check if its in bounds
            rectangle.setY(newY); // moves the paddle
        }
    }
}
