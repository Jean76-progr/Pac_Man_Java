package ijae.xjanelj.view;

import ijae.xjanelj.util.GameConfig;
import ijae.xjanelj.util.ImageResourceManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ResourceBundle;
import ijae.xjanelj.util.SoundManager;
import ijae.xjanelj.util.BackgroundMusic;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class MainMenu extends StackPane {
    private final Stage primaryStage;
    private final ResourceBundle messages;

    public MainMenu(Stage stage) {
        this.primaryStage = stage;
        this.messages = GameConfig.getMessages();

        // Charger les images
        ImageResourceManager.loadImages();

        // Initialiser les sons et la musique
        SoundManager.loadSounds();
        BackgroundMusic.initMusic();
        BackgroundMusic.play();

        setupMenu();
    }

    private void setupMenu() {
        setStyle("-fx-background-color: black;");

        VBox menuBox = new VBox(60);  // Augmenté l'espacement entre les éléments
        menuBox.setAlignment(Pos.CENTER);
        menuBox.setPadding(new javafx.geometry.Insets(50, 0, 50, 0));

        // Titre PAC-MAN en style pixel art
        Text title = createPixelText("PAC-MAN", 60);
        title.setFill(Color.YELLOW);

        // Menu items dans une VBox centrée
        VBox buttonBox = new VBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        String[] menuItems = {
                "1. " + messages.getString("play"),
                "2. " + messages.getString("scores"),
                "3. " + messages.getString("options"),
                "4. " + messages.getString("credits"),
                "5. " + messages.getString("quit")
        };

        for (int i = 0; i < menuItems.length; i++) {
            final int index = i;
            Text menuItem = createPixelText(menuItems[i], 24);
            menuItem.setFill(Color.WHITE);

            // Effet de survol
            menuItem.setOnMouseEntered(e -> menuItem.setFill(Color.YELLOW));
            menuItem.setOnMouseExited(e -> menuItem.setFill(Color.WHITE));
            menuItem.setOnMouseClicked(e -> handleMenuClick(menuItems[index]));

            buttonBox.getChildren().add(menuItem);
        }

        // Row of ghosts
        HBox ghostsBox = createGhostRow();

        // Version text at the bottom
        Text versionText = createPixelText("Version 1.0", 12);
        versionText.setFill(Color.GRAY);

        menuBox.getChildren().addAll(title, buttonBox, ghostsBox, versionText);
        getChildren().add(menuBox);
    }

    private HBox createGhostRow() {
        HBox ghostBox = new HBox(30);
        ghostBox.setAlignment(Pos.CENTER);
        ghostBox.setPadding(new javafx.geometry.Insets(40, 0, 40, 0));

        // Noms des images des fantômes
        String[] ghostImages = {
                "ghost_red",
                "ghost_pink",
                "ghost_blue",
                "ghost_orange"
        };

        for (String ghostImage : ghostImages) {
            Image image = ImageResourceManager.getImage(ghostImage);
            if (image != null) {
                ImageView ghost = new ImageView(image);
                ghost.setFitWidth(30);
                ghost.setFitHeight(30);
                ghost.setPreserveRatio(true);
                ghostBox.getChildren().add(ghost);
            }
        }

        return ghostBox;
    }

    private Text createPixelText(String content, double size) {
        Text text = new Text(content);
        text.setFont(Font.font("Monospace", size));
        return text;
    }

    private void handleMenuClick(String option) {
        if (option.contains(messages.getString("play"))) {
            BackgroundMusic.stop();  // Arrêter la musique avant de commencer le jeu
            showLevelSelector();
        } else if (option.contains(messages.getString("scores"))) {
            showScores();
        } else if (option.contains(messages.getString("options"))) {
            showOptions();
        } else if (option.contains(messages.getString("credits"))) {
            showCredits();
        } else if (option.contains(messages.getString("quit"))) {
            BackgroundMusic.stop();  // Arrêter la musique avant de quitter
            primaryStage.close();
        }
    }

    private void showCredits() {
        CreditsView creditsView = new CreditsView(primaryStage);
        Scene scene = new Scene(creditsView, 630, 730);
        primaryStage.setScene(scene);
    }

    private void showLevelSelector() {
        LevelSelector levelSelector = new LevelSelector(primaryStage);
        Scene scene = new Scene(levelSelector, 630, 730);
        primaryStage.setScene(scene);
    }

    private void showOptions() {
        BackgroundMusic.stop();  // Arrêter la musique avant d'aller aux options
        OptionsView optionsView = new OptionsView(primaryStage);
        Scene scene = new Scene(optionsView, 630, 730);
        primaryStage.setScene(scene);
    }

    private void showScores() {
        BackgroundMusic.stop();  // Arrêter la musique avant d'aller aux scores
        ScoresView scoresView = new ScoresView(primaryStage);
        Scene scene = new Scene(scoresView, 630, 730);
        primaryStage.setScene(scene);
    }
}