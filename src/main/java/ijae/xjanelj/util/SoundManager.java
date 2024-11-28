package ijae.xjanelj.util;

import javafx.scene.media.AudioClip;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SoundManager {
    private static AudioClip chomp;
    private static AudioClip death;
    private static AudioClip ghostEaten;
    private static AudioClip fruit;
    private static AudioClip powerUp;
    private static double volume = 1.0;

    // Map pour suivre l'état de lecture de chaque son
    private static final Map<String, Boolean> isPlaying = new HashMap<>();
    private static final int SOUND_DURATION = 100; // Durée en millisecondes

    public static void loadSounds() {
        try {
            System.out.println("Loading sounds...");

            chomp = loadSound("chomp.wav");
            death = loadSound("death.wav");
            ghostEaten = loadSound("ghost-eaten.wav");
            fruit = loadSound("fruit.wav");
            powerUp = loadSound("power-up.wav");

            // Initialiser l'état de lecture
            isPlaying.put("chomp", false);
            isPlaying.put("death", false);
            isPlaying.put("ghost", false);
            isPlaying.put("fruit", false);
            isPlaying.put("power", false);

            updateVolumes();
        } catch (Exception e) {
            System.err.println("Error loading sounds:");
            e.printStackTrace();
        }
    }

    private static AudioClip loadSound(String filename) {
        try {
            System.out.println("Trying to load sound: " + filename);
            URL resourceUrl = SoundManager.class.getResource("/sounds/" + filename);
            if (resourceUrl == null) {
                System.err.println("Could not find resource: /sounds/" + filename);
                return null;
            }
            String path = resourceUrl.toExternalForm();
            System.out.println("Found sound at: " + path);
            return new AudioClip(path);
        } catch (Exception e) {
            System.err.println("Error loading sound " + filename + ": " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static void play(AudioClip clip, String soundName) {
        if (clip != null && !isPlaying.getOrDefault(soundName, false)) {
            clip.play();
            isPlaying.put(soundName, true);

            // Définir un timer pour réinitialiser l'état après la durée du son
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isPlaying.put(soundName, false);
                }
            }, SOUND_DURATION);
        }
    }

    public static void setVolume(double newVolume) {
        volume = newVolume;
        updateVolumes();
    }

    private static void updateVolumes() {
        if (chomp != null) chomp.setVolume(volume);
        if (death != null) death.setVolume(volume);
        if (ghostEaten != null) ghostEaten.setVolume(volume);
        if (fruit != null) fruit.setVolume(volume);
        if (powerUp != null) powerUp.setVolume(volume);
    }

    // Méthodes de lecture publiques
    public static void playChomp() {
        play(chomp, "chomp");
    }

    public static void playDeath() {
        play(death, "death");
    }

    public static void playFruit() {
        play(fruit, "fruit");
    }

    public static void playPowerUp() {
        play(powerUp, "power");
    }
}