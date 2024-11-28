package ijae.xjanelj.util;

import java.io.*;
import java.util.Properties;
import java.util.Locale;
import java.util.ResourceBundle;

public class GameConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static Properties properties;
    private static ResourceBundle messages;

    static {
        initializeConfig();
    }

    private static void initializeConfig() {
        properties = new Properties();
        try {
            // First try to read existing config file
            File configFile = new File(CONFIG_FILE);
            if (configFile.exists()) {
                try (FileInputStream in = new FileInputStream(configFile)) {
                    properties.load(in);
                }
            } else {
                // If file doesn't exist, create with default values
                properties.setProperty("musicVolume", "0.5");
                properties.setProperty("effectsVolume", "0.5");
                properties.setProperty("language", "en");

                // Create the file with default values
                try (FileOutputStream out = new FileOutputStream(configFile)) {
                    properties.store(out, "Game Configuration");
                }
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize config: " + e.getMessage());
            // Set default values in memory even if file operations fail
            properties.setProperty("musicVolume", "0.5");
            properties.setProperty("effectsVolume", "0.5");
            properties.setProperty("language", "en");
        }

        // Initialize ResourceBundle with current language
        try {
            messages = ResourceBundle.getBundle("messages", new Locale(getLanguage()));
        } catch (Exception e) {
            System.err.println("Failed to load messages bundle: " + e.getMessage());
            // Fallback to English if message bundle loading fails
            messages = ResourceBundle.getBundle("messages", new Locale("en"));
        }
    }

    public static void loadConfig() {
        initializeConfig();
    }

    public static void saveConfig() {
        try (FileOutputStream out = new FileOutputStream(CONFIG_FILE)) {
            properties.store(out, "Game Configuration");
        } catch (IOException e) {
            System.err.println("Failed to save config: " + e.getMessage());
        }
    }

    public static ResourceBundle getMessages() {
        return messages;
    }

    public static void setMusicVolume(double volume) {
        properties.setProperty("musicVolume", String.valueOf(volume));
        BackgroundMusic.setVolume(volume);
        saveConfig();
    }

    public static void setEffectsVolume(double volume) {
        properties.setProperty("effectsVolume", String.valueOf(volume));
        SoundManager.setVolume(volume);
        saveConfig();
    }

    public static void setLanguage(String lang) {
        properties.setProperty("language", lang);
        messages = ResourceBundle.getBundle("messages", new Locale(lang));
        saveConfig();
    }

    public static double getMusicVolume() {
        return Double.parseDouble(properties.getProperty("musicVolume", "0.5"));
    }

    public static double getEffectsVolume() {
        return Double.parseDouble(properties.getProperty("effectsVolume", "0.5"));
    }

    public static String getLanguage() {
        return properties.getProperty("language", "en");
    }
}