package ijae.xjanelj.controller;

import ijae.xjanelj.model.Direction;
import ijae.xjanelj.util.ScoreManager;
import javafx.util.Duration;
import ijae.xjanelj.model.GameBoard;
import ijae.xjanelj.util.BackgroundMusic;
import ijae.xjanelj.util.SoundManager;
import ijae.xjanelj.view.LevelSelector;
import ijae.xjanelj.view.MainMenu;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class GameController {
    private final BorderPane root;
    private final GraphicsContext gc;
    private final GameBoard gameBoard;
    private AnimationTimer gameLoop;
    private Label scoreLabel;
    private Label levelLabel;

    public GameController(int level) {
        SoundManager.loadSounds();
        BackgroundMusic.stop();
        root = new BorderPane();
        root.setStyle("-fx-background-color: black;");

        // Initialize game board
        gameBoard = new GameBoard(21, 21, level);

        // Create UI elements
        setupUI();

        // Initialize canvas and graphics context
        Canvas gameCanvas = new Canvas(gameBoard.getBoardPixelSize(), gameBoard.getBoardPixelSize());
        gc = gameCanvas.getGraphicsContext2D();

        // Add canvas to center
        VBox centerBox = new VBox(gameCanvas);
        centerBox.setAlignment(Pos.CENTER);
        root.setCenter(centerBox);

        // Setup key handler
        gameCanvas.setFocusTraversable(true);
        gameCanvas.setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case UP: gameBoard.movePacman(Direction.UP); break;
                case DOWN: gameBoard.movePacman(Direction.DOWN); break;
                case LEFT: gameBoard.movePacman(Direction.LEFT); break;
                case RIGHT: gameBoard.movePacman(Direction.RIGHT); break;
                case ESCAPE: returnToLevelSelector(); break;
            }
            e.consume();
        });

        startGameLoop();
    }

    private void setupUI() {
        // Top section with score and level
        HBox topBar = new HBox(20);
        topBar.setAlignment(Pos.CENTER);
        topBar.setPadding(new Insets(10));

        scoreLabel = new Label("Score: 0");
        scoreLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

        levelLabel = new Label("Level: " + gameBoard.getCurrentLevel());
        levelLabel.setStyle("-fx-text-fill: white; -fx-font-size: 20px;");

        topBar.getChildren().addAll(scoreLabel, levelLabel);
        root.setTop(topBar);
    }

    private void startGameLoop() {
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!gameBoard.isGameRunning()) {
                    handleGameOver();
                    stop();
                    return;
                }

                gameBoard.update(now);
                updateUI();
                draw();
            }
        };
        gameLoop.start();
    }

    private void updateUI() {
        scoreLabel.setText("Score: " + gameBoard.getScore());
        levelLabel.setText("Level: " + gameBoard.getCurrentLevel());
    }

    private void handleGameOver() {
        SoundManager.playDeath();
        ScoreManager.addScore(gameBoard.getScore(), gameBoard.getCurrentLevel());

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            MainMenu mainMenu = new MainMenu(stage);
            Scene scene = new Scene(mainMenu, 630, 730);
            stage.setScene(scene);
        });
        pause.play();
    }

    private void returnToLevelSelector() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
        Stage stage = (Stage) root.getScene().getWindow();
        LevelSelector levelSelector = new LevelSelector(stage);
        Scene scene = new Scene(levelSelector, 630, 730);
        stage.setScene(scene);
    }

    private void draw() {
        gameBoard.draw(gc);
    }

    public BorderPane getRoot() {
        return root;
    }
}