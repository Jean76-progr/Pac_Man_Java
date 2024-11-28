package ijae.xjanelj.model;

import java.io.*;
import java.util.*;

public class Score implements Serializable {
    private final int score;
    private final String date;
    private final int level;

    public Score(int score, int level) {
        this.score = score;
        this.level = level;
        this.date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
    }

    public int getScore() { return score; }
    public String getDate() { return date; }
    public int getLevel() { return level; }
}