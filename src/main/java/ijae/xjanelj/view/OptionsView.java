package ijae.xjanelj.view;

import ijae.xjanelj.util.BackgroundMusic;
import ijae.xjanelj.util.GameConfig;
import ijae.xjanelj.util.SoundManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ResourceBundle;

public class OptionsView extends VBox {
    private final Stage primaryStage;
    private Slider musicVolumeSlider;
    private Slider effectsVolumeSlider;
    private ComboBox<String> languageComboBox;
    private final ResourceBundle messages;

    public OptionsView(Stage stage) {
        this.primaryStage = stage;
        this.messages = GameConfig.getMessages();
        setupUI();

        // Charger les valeurs actuelles
        musicVolumeSlider.setValue(GameConfig.getMusicVolume() * 100);
        effectsVolumeSlider.setValue(GameConfig.getEffectsVolume() * 100);
        languageComboBox.setValue(GameConfig.getLanguage().equals("fr") ? "Français" : "English");
    }

    private void setupUI() {
        setStyle("-fx-background-color: black;");
        setPadding(new Insets(20));
        setSpacing(20);
        setAlignment(Pos.CENTER);

        Label title = new Label(messages.getString("options"));
        title.setFont(javafx.scene.text.Font.font("Arial", 30));
        title.setTextFill(Color.YELLOW);

        musicVolumeSlider = createVolumeSlider(messages.getString("music_volume"), true);
        effectsVolumeSlider = createVolumeSlider(messages.getString("effects_volume"), false);

        HBox languageBox = createLanguageSelector();
        HBox buttons = new HBox(20, createSaveButton(), createBackButton());
        buttons.setAlignment(Pos.CENTER);

        getChildren().addAll(title,
                createVolumeBox(messages.getString("music_volume"), musicVolumeSlider),
                createVolumeBox(messages.getString("effects_volume"), effectsVolumeSlider),
                languageBox,
                buttons);
    }

    private Slider createVolumeSlider(String name, boolean isMusic) {
        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);

        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (isMusic) {
                BackgroundMusic.setVolume(newVal.doubleValue() / 100.0);
            } else {
                SoundManager.setVolume(newVal.doubleValue() / 100.0);
            }
        });

        return slider;
    }

    private VBox createVolumeBox(String label, Slider slider) {
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);

        Label titleLabel = new Label(label);
        titleLabel.setTextFill(Color.WHITE);

        Label valueLabel = new Label("50%");
        valueLabel.setTextFill(Color.WHITE);

        slider.valueProperty().addListener((obs, oldVal, newVal) ->
                valueLabel.setText(String.format("%.0f%%", newVal.doubleValue())));

        box.getChildren().addAll(titleLabel, slider, valueLabel);
        return box;
    }

    private HBox createLanguageSelector() {
        HBox box = new HBox(10);
        box.setAlignment(Pos.CENTER);

        Label label = new Label(messages.getString("language"));
        label.setTextFill(Color.WHITE);

        languageComboBox = new ComboBox<>();
        languageComboBox.getItems().addAll("English", "Français", "Czech");
        languageComboBox.setValue(GameConfig.getLanguage().equals("fr") ? "Français" : "English");

        box.getChildren().addAll(label, languageComboBox);
        return box;
    }

    private Button createBackButton() {
        Button button = new Button(messages.getString("back"));
        styleButton(button);
        button.setOnAction(e -> returnToMenu());
        return button;
    }

    private Button createSaveButton() {
        Button button = new Button(messages.getString("save"));
        styleButton(button);
        button.setOnAction(e -> saveOptions());
        return button;
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;");
        button.setOnMouseEntered(e ->
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow; -fx-border-color: yellow;"));
        button.setOnMouseExited(e ->
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: white; -fx-border-color: white;"));
    }

    private void saveOptions() {
        // Sauvegarder les volumes
        GameConfig.setMusicVolume(musicVolumeSlider.getValue() / 100.0);
        GameConfig.setEffectsVolume(effectsVolumeSlider.getValue() / 100.0);

        // Sauvegarder et appliquer la langue
        String newLang = languageComboBox.getValue().equals("Français") ? "fr" : "en";
        GameConfig.setLanguage(newLang);

        returnToMenu();
    }

    private void returnToMenu() {
        MainMenu mainMenu = new MainMenu(primaryStage);
        Scene menuScene = new Scene(mainMenu, 630, 730);
        primaryStage.setScene(menuScene);
    }
}