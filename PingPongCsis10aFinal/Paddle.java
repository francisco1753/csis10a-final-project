
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

/**
 * Write a description of JavaFX class Paddle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// this class makes the paddles and configures them. it also lets them move

public class Paddle {
    private Rectangle rectangle;
    private static final int paddle_width = 15;
    private static final int paddle_height = 80;
    
    public Paddle(double startX, double startY) {
        rectangle = new Rectangle(paddle_width, paddle_height, Color.CRIMSON);
        rectangle.setX(startX);
        rectangle.setY(startY);
    }
    
    public Rectangle getRectangle() {
        return rectangle;
    }
    
    
    public void move(int dy, int windowHeight) {
        double newY = rectangle.getY() + dy;
        if (newY >= 0 && newY <= windowHeight - paddle_height) {
            rectangle.setY(newY);
        }
    }
}
