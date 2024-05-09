import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.HashSet;
import java.util.Set;
// class to handle main game loop, updates game state, checking winning condition
// also manages paddle movement and interactions with the ball
public class GameLoop extends AnimationTimer {
    private final Pane gamePane; // area for game
    private final Paddle leftPaddle;
    private final Paddle rightPaddle;
    private final Ball ball;
    private final Score score;
    private final Set<KeyCode> keysPressed = new HashSet<>();// set for pressed keys
    private final int windowWidth; 
    private final int windowHeight;
    private boolean activeGame = true; // controls game loop
// initializes game loop with required objects
    public GameLoop(Pane gamePane, Paddle leftPaddle, Paddle rightPaddle, Ball ball, Score score, int windowWidth, int windowHeight) {
        this.gamePane = gamePane;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.ball = ball;
        this.score = score;
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        }

    @Override
    public void handle(long now) {
        if (activeGame) { //makes sure game is running
            updateGame();   // updates game state
            checkWinner();  //checks if game should end
        }
    }

    private void updateGame() {
        ball.updatePosition(); //updates ball position

        // checks if ball hits top or bottom 
        if (ball.hitsTopOrBottom(windowHeight)) {
            ball.bounceVertical();  // reverses the directions
        }
        // same thing but with the paddle
        if (ball.getCircle().intersects(leftPaddle.getRectangle().getBoundsInParent()) || 
            ball.getCircle().intersects(rightPaddle.getRectangle().getBoundsInParent())) {
            ball.bounceHorizontal();
        }

        double ballRadius = ball.getRadius(); // accesses ball radius
        //checks if ball is out of bounds by detecting the x axis. if it is, score increases
        if (ball.getCircle().getCenterX() - ballRadius <= 0) {
            score.increaseRightScore();
            ball.reset(windowWidth / 2, windowHeight / 2);  //puts ball back to center
        } else if (ball.getCircle().getCenterX() + ballRadius >= windowWidth) {
            score.increaseLeftScore();
            ball.reset(windowWidth / 2, windowHeight / 2);
        }

        // keys for paddle movement
        if (keysPressed.contains(KeyCode.W)) {
            leftPaddle.move(-10, windowHeight);
        } else if (keysPressed.contains(KeyCode.S)) {
            leftPaddle.move(10, windowHeight);
        }

        if (keysPressed.contains(KeyCode.UP)) {
            rightPaddle.move(-10, windowHeight);
        } else if (keysPressed.contains(KeyCode.DOWN)) {
            rightPaddle.move(10, windowHeight);
        }
    }
        // checks for who won
         private void checkWinner() {
            if (score.getLeftScore() >= 5) { 
                declareWinner("Left");
            } else if (score.getRightScore() >= 5) { 
            declareWinner("Right");
            }              
    }

        //declares who won
    private void declareWinner(String winner) {
        activeGame = false; //also stops the game
        // configuring the winner text
        Text winnerText = new Text(winner + " player wins");
        winnerText.setFont(Font.font("Comic Sans MS", 30));
        winnerText.setFill(javafx.scene.paint.Color.WHITE);
        winnerText.setX((windowWidth - winnerText.getBoundsInLocal().getWidth()) / 2);
        winnerText.setY(windowHeight / 2 - 120);

        gamePane.getChildren().add(winnerText);

        // funny image that appears at the end of the game
        Image gojo = new Image("file:gojo.png");
        ImageView imageView = new ImageView(gojo);
        imageView.setFitWidth(250);
        imageView.setFitHeight(250);
        imageView.setX(10);
        imageView.setY(windowHeight - 275); 

        gamePane.getChildren().add(imageView);
    }

    public Set<KeyCode> getKeysPressed() {
        return keysPressed;
    }
}
