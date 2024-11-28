package ijae.xjanelj.view;

import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class GameScene extends BorderPane {
    public GameScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GameView.fxml"));
            BorderPane gameView = loader.load();
            setCenter(gameView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}