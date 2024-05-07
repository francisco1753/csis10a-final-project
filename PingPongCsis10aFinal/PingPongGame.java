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
    private static final int window_width = 600;
    private static final int window_height = 400;
     private MediaPlayer backgroundMusic;
     
     
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, window_width, window_height, Color.CORNFLOWERBLUE);

        
        Paddle leftPaddle = new Paddle(20, (window_height - 80) / 2);
        Paddle rightPaddle = new Paddle(window_width - 35, (window_height - 80) / 2);
        Ball ball = new Ball(window_width / 2, window_height / 2, 2.5, 2.5);
        Score score = new Score(); 

        
        score.getLeftScoreText().setX(50); 
        score.getLeftScoreText().setY(50);
        score.getRightScoreText().setX(window_width - 100); 
        score.getRightScoreText().setY(50);

        root.getChildren().addAll(leftPaddle.getRectangle(), rightPaddle.getRectangle(), ball.getCircle(),
                                  score.getLeftScoreText(), score.getRightScoreText());

       
        GameLoop gameLoop = new GameLoop(root, leftPaddle, rightPaddle, ball, score, window_width, window_height);

        scene.setOnKeyPressed(event -> gameLoop.getKeysPressed().add(event.getCode()));
        scene.setOnKeyReleased(event -> gameLoop.getKeysPressed().remove(event.getCode()));

        gameLoop.start(); 

        String musicPath = Paths.get("background_music1.mp3").toUri().toString(); 
        backgroundMusic = new MediaPlayer(new Media(musicPath));
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);  
        backgroundMusic.play();
        
        String catInBoxTextArt =  """
        ,-.       _,---._ __  / \\
        /  )    .-'       `./ /   \\
       (  (   ,' first      `/    /|
        \\  `-"   to        \\'\\   / |
         `.     15       ,  \\ \\ /  |
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
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}  