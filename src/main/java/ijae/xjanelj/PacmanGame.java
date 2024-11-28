package ijae.xjanelj;

import ijae.xjanelj.view.MainMenu;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PacmanGame extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Créer le menu principal
        MainMenu mainMenu = new MainMenu(primaryStage);

        // Créer la scène
        Scene scene = new Scene(mainMenu, 630, 730);

        // Configurer la fenêtre principale
        primaryStage.setTitle("Pac-Man");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}