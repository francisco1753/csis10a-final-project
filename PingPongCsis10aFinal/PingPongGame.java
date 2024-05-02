import javafx.scene.shape.Circle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
/**
 * Write a description of JavaFX class PingPongGame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class PingPongGame extends Application {
    // these variables are for the window of the application.
    //paddle sizes, and ball radius
    private static final int window_width = 600;
    private static final int window_height = 400;
    private static final int paddle_width = 15;
    private static final int paddle_height = 80;
    private static final int ball_radius = 10;
    // variables for paddle and making the ball
    private Rectangle leftPaddle;
    private Rectangle rightPaddle;
    private Circle ball;
    // variables for how fast the ball will travel
    private double ballXSpeed = 2.5;
    private double ballYSpeed = 2.5;
    // variables for the score
    private int leftScore = 0;
    private int rightScore = 0;
    // text for the score
    private Text leftScoreText;
    private Text rightScoreText;
    // show if game is running or not
    private boolean activeGame = true;
    // variable for the background music
    private MediaPlayer backgroundMusic;
    //animation timer for game loop
    private AnimationTimer gameLoop;
    //for the keys currently pressed
    private Set<KeyCode> keysPressed = new HashSet<>();
    public void start(Stage primaryStage){
        //making the scene
        Pane root = new Pane();        
        Scene scene = new Scene(root, window_width, window_height, Color.CORNFLOWERBLUE);
        //making the paddles and ball
        leftPaddle = new Rectangle(paddle_width, paddle_height, Color.CRIMSON);
        rightPaddle = new Rectangle(paddle_width, paddle_height, Color.CRIMSON);
        ball = new Circle(ball_radius, Color.CRIMSON);
        //placing the paddles and ball where they should be
        leftPaddle.setX(20);
        leftPaddle.setY((window_height - paddle_height) / 2);
        
        rightPaddle.setX(window_width - 20 - paddle_width);
        rightPaddle.setY((window_height - paddle_height) / 2);
        
        ball.setCenterX(window_width / 2);
        ball.setCenterY(window_height / 2);
        //configuring the score text
        leftScoreText = new Text("Left: 0");
        leftScoreText.setFont(Font.font("Comic Sans MS", 20));
        leftScoreText.setFill(Color.WHITE);
        leftScoreText.setX(50);
        leftScoreText.setY(50);
        // same thing but for the right side
        rightScoreText = new Text("Right: 0");
        rightScoreText.setFont(Font.font("Comic Sans MS", 20));
        rightScoreText.setFill(Color.WHITE);
        rightScoreText.setX(window_width - 100);
        rightScoreText.setY(50);
        //adding it to the scene
        root.getChildren().addAll(leftPaddle, rightPaddle, ball, leftScoreText, rightScoreText);
        // silly cat in box
        String catInBoxTextArt =  """
        ,-.       _,---._ __  / \\
        /  )    .-'       `./ /   \\
       (  (   ,' first      `/    /|
        \\  `-"   to        \\'\\   / |
         `.     5       ,  \\ \\ /  |
          /`.   wins   ,'-`----Y   |
         (   SIGMA    ;        |   '
         |  ,-.    ,-'         |  /
         |  | (   |         | /
         )  |  \\  `.___________|/
         `--'   `--'""";
         //configuring the silly cat art
       Text textArt = new Text(catInBoxTextArt);
       textArt.setFont(Font.font("Monospace", 10));
       textArt.setFill(Color.WHITE);
       textArt.setX((window_width - textArt.getBoundsInLocal().getWidth()) / 2);
       textArt.setY(window_height / 2);
       
       root.getChildren().add(textArt);
       // setting up the media player and looping it
       String musicPath = Paths.get("background_music.mp3").toUri().toString();
       backgroundMusic = new MediaPlayer(new Media(musicPath));
       backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
       backgroundMusic.play();
       //paddle movement with keyboard input using movePaddle method
       scene.setOnKeyPressed(event -> keysPressed.add(event.getCode()));
       scene.setOnKeyReleased(event -> keysPressed.remove(event.getCode()));
       
        
        
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (activeGame) {
                    updateGame();
                }
            }
        };
    
        gameLoop.start();
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
        
    
    
    //helper method for paddle movement
    
    
    private void updateGame() {
        // moves the ball
        ball.setCenterX(ball.getCenterX() + ballXSpeed);
        ball.setCenterY(ball.getCenterY() + ballYSpeed);
        // ball can bounce from the top and bottom
        if (ball.getCenterY() - ball_radius <= 0 || ball.getCenterY() + ball_radius >= window_height) {
            ballYSpeed *= -1;
        }
        // ball can bounce of paddles
        if (ball.intersects(leftPaddle.getBoundsInParent()) || ball.intersects(rightPaddle.getBoundsInParent())) {
            ballXSpeed *= -1;
        }
        
        // the logic for when the ball goes past paddle, calls the methods
        // to check winner, reset ball, and update score
        if (ball.getCenterX() - ball_radius <= 0) {
            rightScore +=1;
            resetBall();
            updateScore();
            checkWinner();
        }   else if (ball.getCenterX() + ball_radius >= window_width) {
            leftScore += 1;
            resetBall();
            updateScore();
            checkWinner();
        }
        // movement keys
        if (keysPressed.contains(KeyCode.W)) {
            movePaddle(leftPaddle, -10);
        } else if (keysPressed.contains(KeyCode.S)) {
            movePaddle(leftPaddle, 10);
        }
        
        if (keysPressed.contains(KeyCode.UP)) {
            movePaddle(rightPaddle, -10);
        } else if (keysPressed.contains(KeyCode.DOWN)) {
            movePaddle(rightPaddle, 10);
        }
        
    }
    // for the paddles to move
    private void movePaddle(Rectangle paddle, int dy) {
        double newY = paddle.getY() + dy;
        if (newY >= 0 && newY <= window_height - paddle_height) {
            paddle.setY(newY);
        }
    }
    
    
    //helper method to get ball back in place
    private void resetBall() {
        ball.setCenterX(window_width / 2);
        ball.setCenterY(window_height /2);
        ballXSpeed = -ballXSpeed; //changes the direction after it resets
    }
    // helper method to update score
    private void updateScore() {
        leftScoreText.setText("left: " + leftScore);
        rightScoreText.setText("right: " + rightScore);
    }
    //helper method for the game to see who won
    private void checkWinner() {
        if (leftScore >= 5) {
            declareWinner("left");
        }   else if (rightScore >= 5) {
            declareWinner("right");
        }
    }
    // tells who wins at the end of game
    private void declareWinner(String winner) {
        activeGame = false; //this stops the game when the winner is declared
        
        Text winnerText = new Text(winner + " player wins");
        winnerText.setFont(Font.font("Comic Sans MS", 30));
        winnerText.setFill(Color.WHITE);
        winnerText.setX((window_width - winnerText.getBoundsInLocal().getWidth()) /2);
        winnerText.setY(window_height / 2 - 125); 
        
        Pane gamePane = (Pane) leftPaddle.getParent();
        gamePane.getChildren().add(winnerText);
        // funny image that shows at the end of the game
        Image gojoImage = new Image("gojo.png");
        ImageView imageView = new ImageView(gojoImage);
        imageView.setFitWidth(250); 
        imageView.setFitHeight(250);
        imageView.setX(10);
        imageView.setY(window_height - 300);
        
        gamePane.getChildren().add(imageView);
    }
    
}