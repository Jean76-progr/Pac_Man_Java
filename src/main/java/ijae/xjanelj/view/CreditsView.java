package ijae.xjanelj.view;

import ijae.xjanelj.util.GameConfig;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ResourceBundle;

public class CreditsView extends VBox {
    private final Stage primaryStage;
    private final ResourceBundle messages;
    private VBox scrollingCredits;

    public CreditsView(Stage stage) {
        this.primaryStage = stage;
        this.messages = GameConfig.getMessages();
        setupUI();
        startScrollingAnimation();
    }

    private void setupUI() {
        setStyle("-fx-background-color: black;");
        setPadding(new Insets(20));
        setSpacing(20);
        setAlignment(Pos.CENTER);

        // Titre principal
        Text title = createStyledText("PAC-MAN", 48, Color.YELLOW);

        // Conteneur pour les crédits défilants
        scrollingCredits = new VBox(30);
        scrollingCredits.setAlignment(Pos.CENTER);

        // Version du jeu
        Text version = createStyledText("Version 1.0", 24, Color.WHITE);

        // Développeurs
        Text devTitle = createStyledText("Developers", 28, Color.YELLOW);
        Text devNames = createStyledText("Jean JANEL", 20, Color.WHITE);

        // Technologies utilisées
        Text techTitle = createStyledText("Technologies", 28, Color.YELLOW);
        Text techList = createStyledText("Java 21\nJavaFX\nGradle", 20, Color.WHITE);

        // Remerciements
        Text thanksTitle = createStyledText("Special Thanks", 28, Color.YELLOW);
        Text thanksList = createStyledText(
                "Original Pac-Man game by Namco\n" +
                        "ESIR\n" +
                        "All beta testers", 20, Color.WHITE);

        // Année
        Text year = createStyledText("© 2024", 24, Color.WHITE);

        // Ajouter tous les éléments au conteneur défilant
        scrollingCredits.getChildren().addAll(
                version,
                devTitle, devNames,
                techTitle, techList,
                thanksTitle, thanksList,
                year
        );

        // Bouton retour
        Button backButton = new Button(messages.getString("back"));
        styleButton(backButton);
        backButton.setOnAction(e -> returnToMenu());

        // Ajouter tous les éléments à la vue principale
        getChildren().addAll(title, scrollingCredits, backButton);
    }

    private Text createStyledText(String content, double size, Color color) {
        Text text = new Text(content);
        text.setFont(Font.font("Monospace", size));
        text.setFill(color);
        text.setTextAlignment(TextAlignment.CENTER);
        return text;
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;");
        button.setOnMouseEntered(e ->
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow; -fx-border-color: yellow;")
        );
        button.setOnMouseExited(e ->
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;")
        );
    }

    private void startScrollingAnimation() {
        TranslateTransition scrollTransition = new TranslateTransition(Duration.seconds(20), scrollingCredits);
        scrollTransition.setFromY(600); // Commence en bas de l'écran
        scrollTransition.setToY(-1000); // Termine au-dessus de l'écran
        scrollTransition.setCycleCount(TranslateTransition.INDEFINITE);
        scrollTransition.play();
    }

    private void returnToMenu() {
        MainMenu mainMenu = new MainMenu(primaryStage);
        Scene menuScene = new Scene(mainMenu, 630, 730);
        primaryStage.setScene(menuScene);
    }
}