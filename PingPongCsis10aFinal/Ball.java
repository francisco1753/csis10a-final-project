import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;


public class Ball {
    private static final int ball_radius = 10; //sets radius
    private Circle circle;
    private double xSpeed;
    private double ySpeed;
    
    
    // initializes ball with start position and speed
    public Ball(double startX, double startY, double initialXSpeed, double initialYSpeed) {
        circle = new Circle(ball_radius, Color.CRIMSON);
        circle.setCenterX(startX);  // sets initial coords
        circle.setCenterY(startY);
         this.xSpeed = initialXSpeed * 2; // doubles the speed of ball
        this.ySpeed = initialYSpeed * 2;
    }
    // getter for ball
    public Circle getCircle() {
        return circle;
        
        }
    // getter for radius
    public double getRadius() {
        return ball_radius;
    }
    // updates ball position based on speed
    public void updatePosition() {
        circle.setCenterX(circle.getCenterX() + xSpeed);
        circle.setCenterY(circle.getCenterY() + ySpeed);
    }
    // reverses directions
    public void bounceVertical() {
        ySpeed *= -1;
    }
    
    public void bounceHorizontal() {
        xSpeed *= -1;
    }
    // resets the ball
    public void reset(double centerX, double centerY) {
        circle.setCenterX(centerX);
        circle.setCenterY(centerY);
        bounceHorizontal(); 
    }
    // check if the ball hits top or bottom
    public boolean hitsTopOrBottom(int windowHeight) {
        return circle.getCenterY() - ball_radius <= 0 || circle.getCenterY() + ball_radius >= windowHeight;
    }
    // or if its out of the game window
    public boolean isOutOfBounds(int windowWidth) {
        return circle.getCenterX() - ball_radius <= 0 || circle.getCenterX() + ball_radius >= windowWidth;

    }
} 
