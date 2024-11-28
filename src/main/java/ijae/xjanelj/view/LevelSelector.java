package ijae.xjanelj.view;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ijae.xjanelj.controller.GameController;

public class LevelSelector extends VBox {
    private final Stage primaryStage;

    public LevelSelector(Stage stage) {
        this.primaryStage = stage;
        setStyle("-fx-background-color: black;");
        setPadding(new Insets(20));
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Titre
        Text title = new Text("SELECT LEVEL");
        title.setFont(javafx.scene.text.Font.font("Arial", 30));
        title.setFill(Color.YELLOW);

        // Conteneur pour les boutons de niveau
        HBox levelButtons = new HBox(20);
        levelButtons.setAlignment(Pos.CENTER);

        // Créer 4 boutons de niveau
        for (int i = 1; i <= 4; i++) {
            Button levelBtn = createLevelButton(i);
            levelButtons.getChildren().add(levelBtn);
        }

        // Bouton retour
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;");
        backButton.setOnAction(e -> returnToMenu());
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow; -fx-border-color: yellow;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;"));

        getChildren().addAll(title, levelButtons, backButton);
    }

    private Button createLevelButton(int level) {
        Button btn = new Button("Level " + level);
        btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;");

        btn.setOnAction(e -> startGame(level));

        btn.setOnMouseEntered(e ->
                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow; -fx-border-color: yellow;")
        );

        btn.setOnMouseExited(e ->
                btn.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;")
        );

        return btn;
    }

    private void startGame(int level) {
        GameController gameController = new GameController(level);
        Scene gameScene = new Scene(gameController.getRoot(), 630, 730);
        primaryStage.setScene(gameScene);
    }

    private void returnToMenu() {
        MainMenu mainMenu = new MainMenu(primaryStage);
        Scene menuScene = new Scene(mainMenu, 630, 730);
        primaryStage.setScene(menuScene);
    }
}