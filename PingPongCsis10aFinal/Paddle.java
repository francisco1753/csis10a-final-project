import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

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
