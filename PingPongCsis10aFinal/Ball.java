import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

/**
 * Write a description of JavaFX class Ball here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
// this class helps make the ball, configures it, and has the logic
// it has things like speed of the ball
public class Ball {
    private Circle circle;
    private static final int ball_radius = 10;
    private double speedX;
    private double speedY;
    
    
    public Ball(double startX, double startY) {
        Circle circle = new Circle(ball_radius, Color.CRIMSON);
        circle.setX(startX);
        circle.setY(startY);
    }
    
    public Circle getCircle() {
        return circle;        
    }
}
    

   
