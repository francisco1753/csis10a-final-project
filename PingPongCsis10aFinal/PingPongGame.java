import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.nio.file.Paths;

public class PingPongGame extends Application {
    private static final int window_width = 600; // variables for window width
    private static final int window_height = 400;// and height, these are
    // immutable so they won't change
     private MediaPlayer backgroundMusic;
     // media player variable for the music
     
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, window_width, window_height, Color.CORNFLOWERBLUE);
        // game scene with the background
        // variable for paddles and balls
        Paddle leftPaddle = new Paddle(20, (window_height - 80) / 2); 
        Paddle rightPaddle = new Paddle(window_width - 35, (window_height - 80) / 2);
        Ball ball = new Ball(window_width / 2, window_height / 2, 2.5, 2.5);
        Score score = new Score(); 

        
        score.getLeftScoreText().setX(50); 
        score.getLeftScoreText().setY(50);
        score.getRightScoreText().setX(window_width - 100); 
        score.getRightScoreText().setY(50);
        //adds stuff to the scene
        root.getChildren().addAll(leftPaddle.getRectangle(), rightPaddle.getRectangle(), ball.getCircle(),
                                  score.getLeftScoreText(), score.getRightScoreText());

       // game loop object
        GameLoop gameLoop = new GameLoop(root, leftPaddle, rightPaddle, ball, score, window_width, window_height);
        // pressing and releasing keys
        scene.setOnKeyPressed(event -> gameLoop.getKeysPressed().add(event.getCode()));
        scene.setOnKeyReleased(event -> gameLoop.getKeysPressed().remove(event.getCode()));

        gameLoop.start(); 
        //goes through the directory to find mp3 file
        String musicPath = Paths.get("background_music1.mp3").toUri().toString(); 
        // adds it to the media
        backgroundMusic = new MediaPlayer(new Media(musicPath));
        //loops it 
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);  
        backgroundMusic.play();
        
        String catInBoxTextArt =  """
        ,-.       _,---._ __  / \\
        /  )    .-'       `./ /   \\
       (  (   ,' first      `/    /|
        \\  `-"   to        \\'\\   / |
         `.        5     ,  \\ \\ /  |
          /`.   wins   ,'-`----Y   |
         (            ;        |   '
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
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}  