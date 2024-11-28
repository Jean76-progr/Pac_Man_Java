package ijae.xjanelj.util;

import ijae.xjanelj.model.Score;
import java.io.*;
import java.util.*;

public class ScoreManager {
    private static final String SCORES_FILE = "scores.dat";
    private static List<Score> scores = new ArrayList<>();

    public static void addScore(int score, int level) {
        scores.add(new Score(score, level));
        scores.sort((a, b) -> b.getScore() - a.getScore());
        saveScores();
    }

    public static List<Score> getScores() {
        loadScores();
        return new ArrayList<>(scores);
    }

    private static void saveScores() {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SCORES_FILE))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadScores() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(SCORES_FILE))) {
            scores = (List<Score>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            scores = new ArrayList<>();
        }
    }
}