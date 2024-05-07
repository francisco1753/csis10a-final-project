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
    // main class, initializes game components and sets up game loop
public class PingPongGame extends Application {
    private static final int window_width = 600;    //changes window height
    private static final int window_height = 400;           // and width
     private MediaPlayer backgroundMusic;   // for background music
     
     
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, window_width, window_height, Color.CORNFLOWERBLUE);

        // initializes paddle, score, and ball
        Paddle leftPaddle = new Paddle(20, (window_height - 80) / 2);
        Paddle rightPaddle = new Paddle(window_width - 35, (window_height - 80) / 2);
        Ball ball = new Ball(window_width / 2, window_height / 2, 2.5, 2.5);
        Score score = new Score(); 

        //sets their positions
        score.getLeftScoreText().setX(50); 
        score.getLeftScoreText().setY(50);
        score.getRightScoreText().setX(window_width - 100); 
        score.getRightScoreText().setY(50);

        root.getChildren().addAll(leftPaddle.getRectangle(), rightPaddle.getRectangle(), ball.getCircle(),
                                  score.getLeftScoreText(), score.getRightScoreText());

        // sets up game loop
        GameLoop gameLoop = new GameLoop(root, leftPaddle, rightPaddle, ball, score, window_width, window_height);

        scene.setOnKeyPressed(event -> gameLoop.getKeysPressed().add(event.getCode()));
        scene.setOnKeyReleased(event -> gameLoop.getKeysPressed().remove(event.getCode()));
        // starts it
        gameLoop.start(); 
            //file path for music
        String musicPath = Paths.get("background_music1.mp3").toUri().toString();  
        backgroundMusic = new MediaPlayer(new Media(musicPath));
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);  //loops music
        backgroundMusic.play(); //plays the music
        
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
        
        primaryStage.setScene(scene);   //sets scene on stage
        primaryStage.show();    //show the stage
    }

    
}