package ijae.xjanelj.util;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ImageResourceManager {
    private static final Map<String, Image> images = new HashMap<>();

    public static void loadImages() {
        try {
            // Charger les images
            images.put("pacman", loadImage("pacman.png"));
            images.put("ghost_red", loadImage("ghost_red.png"));
            images.put("ghost_pink", loadImage("ghost_pink.png"));
            images.put("ghost_blue", loadImage("ghost_blue.png"));
            images.put("ghost_orange", loadImage("ghost_orange.png"));
            images.put("key", loadImage("key.png"));

            System.out.println("All images loaded successfully!");
        } catch (Exception e) {
            System.err.println("Error loading images: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static Image loadImage(String filename) {
        try {
            String path = Objects.requireNonNull(ImageResourceManager.class.getResource("/images/" + filename)).toExternalForm();
            return new Image(path);
        } catch (Exception e) {
            System.err.println("Error loading image " + filename + ": " + e.getMessage());
            return null;
        }
    }

    public static Image getImage(String name) {
        return images.get(name);
    }
}