import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class Score {
    private Text leftScoreText;
    private Text rightScoreText;
    private int leftScore = 0;
    private int rightScore = 0;
    // initializes the score object
    public Score() {
        leftScoreText = new Text("Left: 0");
        rightScoreText = new Text("Right: 0");
        
        leftScoreText.setFont(javafx.scene.text.Font.font("Comic Sans MS", 20));
        leftScoreText.setFill(Color.WHITE);

        rightScoreText.setFont(javafx.scene.text.Font.font("Comic Sans MS", 20));
        rightScoreText.setFill(Color.WHITE);
    }
    
    // getters for score text objects
    public Text getLeftScoreText() {
        return leftScoreText;
    }

    public Text getRightScoreText() {
        return rightScoreText;
    }
    // increases players score
    public void increaseLeftScore() {
        leftScore++;
        leftScoreText.setText("Left: " + leftScore);
    }

    public void increaseRightScore() {
        rightScore++;
        rightScoreText.setText("Right: " + rightScore);
    }
        // getter for players score
    public int getLeftScore() {
        return leftScore;
    }

    public int getRightScore() {
        return rightScore;
    }
}
