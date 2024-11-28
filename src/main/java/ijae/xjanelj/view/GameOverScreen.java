package ijae.xjanelj.view;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameOverScreen extends StackPane {
    private final Stage primaryStage;

    public GameOverScreen(Stage stage, int score, int level) {
        this.primaryStage = stage;

        setStyle("-fx-background-color: black;");

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new Insets(20));

        // Game Over Text
        Text gameOverText = new Text("GAME OVER");
        gameOverText.setFont(javafx.scene.text.Font.font("Arial", 48));
        gameOverText.setFill(Color.RED);

        // Score
        Text scoreText = new Text("Score: " + score);
        scoreText.setFont(javafx.scene.text.Font.font("Arial", 24));
        scoreText.setFill(Color.WHITE);

        // Level
        Text levelText = new Text("Level: " + level);
        levelText.setFont(javafx.scene.text.Font.font("Arial", 24));
        levelText.setFill(Color.WHITE);


        Button mainMenuButton = createButton("Main Menu");
        mainMenuButton.setOnAction(e -> returnToMenu());

    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;");

        button.setOnMouseEntered(e ->
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow; -fx-border-color: yellow;")
        );

        button.setOnMouseExited(e ->
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;")
        );

        return button;
    }

    private void returnToMenu() {
        MainMenu mainMenu = new MainMenu(primaryStage);
        Scene menuScene = new Scene(mainMenu, 630, 730);
        primaryStage.setScene(menuScene);
    }
}