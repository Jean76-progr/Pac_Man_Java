package ijae.xjanelj.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.Objects;

public class BackgroundMusic {
    private static MediaPlayer mediaPlayer;
    private static double volume = 0.5;
    private static boolean isPlaying = false;

    public static void initMusic() {
        try {
            System.out.println("Initializing background music...");

            // Si une musique est déjà en cours, l'arrêter
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }

            String path = Objects.requireNonNull(BackgroundMusic.class.getResource("/sounds/pacman_beginning.wav")).toExternalForm();
            Media media = new Media(path);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            System.out.println("Background music initialized successfully!");
        } catch (Exception e) {
            System.err.println("Error loading background music:");
            e.printStackTrace();
        }
    }

    public static void play() {
        if (mediaPlayer != null && !isPlaying) {
            mediaPlayer.play();
            isPlaying = true;
        }
    }

    public static void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            isPlaying = false;
        }
    }

    public static void setVolume(double newVolume) {
        volume = newVolume;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }
}