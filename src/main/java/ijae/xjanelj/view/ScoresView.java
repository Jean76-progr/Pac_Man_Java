package ijae.xjanelj.view;

import ijae.xjanelj.model.Score;
import ijae.xjanelj.util.ScoreManager;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class ScoresView extends VBox {
    private final Stage primaryStage;

    public ScoresView(Stage stage) {
        this.primaryStage = stage;
        setStyle("-fx-background-color: black;");
        setPadding(new Insets(20));
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Titre
        Label title = new Label("HIGH SCORES");
        title.setFont(Font.font("Arial", 30));
        title.setTextFill(Color.YELLOW);

        // Scores
        VBox scoresBox = new VBox(10);
        scoresBox.setAlignment(Pos.CENTER);

        List<Score> scores = ScoreManager.getScores();
        for (Score score : scores) {
            HBox scoreRow = new HBox(20);
            scoreRow.setAlignment(Pos.CENTER);

            Label scoreLabel = new Label(String.format("%06d", score.getScore()));
            scoreLabel.setTextFill(Color.WHITE);
            Label levelLabel = new Label("Level " + score.getLevel());
            levelLabel.setTextFill(Color.WHITE);
            Label dateLabel = new Label(score.getDate());
            dateLabel.setTextFill(Color.WHITE);

            scoreRow.getChildren().addAll(scoreLabel, levelLabel, dateLabel);
            scoresBox.getChildren().add(scoreRow);
        }

        // Bouton retour
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;");
        backButton.setOnAction(e -> returnToMenu());
        backButton.setOnMouseEntered(e ->
                backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow; -fx-border-color: yellow;")
        );
        backButton.setOnMouseExited(e ->
                backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;")
        );

        getChildren().addAll(title, scoresBox, backButton);
    }

    private void returnToMenu() {
        MainMenu mainMenu = new MainMenu(primaryStage);
        Scene menuScene = new Scene(mainMenu, 630, 730);
        primaryStage.setScene(menuScene);
    }
}