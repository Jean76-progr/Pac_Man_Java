package ijae.xjanelj.model;

import ijae.xjanelj.model.entities.*;
import ijae.xjanelj.model.field.*;
import ijae.xjanelj.util.SoundManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private static final int BOARD_SIZE = 15;
    private static final int CELL_SIZE = 40;
    private final Field[][] board;
    private Pacman pacman;
    private final List<Ghost> ghosts;
    private int score;
    private int currentLevel;
    private long lastGhostMove = 0;
    private static final long GHOST_MOVE_INTERVAL = 300_000_000; // 300ms en nanosecondes
    private static final long PACMAN_MOVE_INTERVAL = 100_000_000; // 100ms en nanosecondes
    private Direction pacmanDirection;
    private boolean gameRunning;
    private static final long GHOST_MOVE_DELAY = 500;

    // Labyrinthe inspiré du Pac-Man classique
    private static final String[][] LEVELS = {
            // Niveau 1 : Simple et ouvert
            {
                    "WWWWWWWWWWWWWWWWWWWWW",
                    "W....K....W.........W",
                    "W.WW.WWW..W..WWW.WW.W",
                    "W...................W",
                    "W.WW.W.WWWWWWW.W.WW.W",
                    "W....W....W....W....W",
                    "WWWW.WWW..W..WWW.WWWW",
                    "W....W.........W....W",
                    "W.WW.W.WWW-WWW.W.WW.W",
                    "W.WW.....G.G.....WW.W",
                    "W.WW.WWW.WWW.WWW.WW.W",
                    "W.WW.....P.D.....WW.W",
                    "W.WW.W.WWWWWWW.W.WW.W",
                    "W....W....W....W....W",
                    "WWWW.WWW..W..WWW.WWWW",
                    "W....W.........W....W",
                    "W.WW.W.WWWWWWW.W.WW.W",
                    "W.WW................W",
                    "W.WW.WWW..W..WWW.WW.W",
                    "W.........W.........W",
                    "WWWWWWWWWWWWWWWWWWWW"
            },
            // Niveau 2 : Plus complexe avec tunnels et fantômes
            {
                    "WWWWWWWWWWWWWWWWWWWWW",
                    "W...................W",
                    "W.WWWW.WWWWW.WWWWW.W",
                    "W.W.K..W...W...G.W.W",
                    "W.W.WW.W.W.W.WWW.W.W",
                    "W.W..G.W.W.W.....W.W",
                    "W.WWWW.W.W.WWWWW.W.W",
                    "W.........P........GW",
                    "W.WWWW.WWWDWWW.WWW.W",
                    "W.W.G..W.....W.....W",
                    "W.W.WW.W.WWW.W.WWW.W",
                    "W.W..W.....W...G...W",
                    "W.WW.WWWWW.W.WWWWW.W",
                    "W..G.W.....W.......W",
                    "WWWW.W.WWW.WWWWWWW.W",
                    "W....W.W.....G.....W",
                    "W.WWWW.W.WWWWWWWWW.W",
                    "W.....G.W..........W",
                    "W.WWWWW.WWWWW.WWW.WW",
                    "W........G..........W",
                    "WWWWWWWWWWWWWWWWWWWW"
            },

            // Niveau 3 : Labyrinthe avec plus de fantômes
            {
                    "WWWWWWWWWWWWWWWWWWWWW",
                    "WP.....K............W",
                    "WWWWW.WWWWW.WWWWW..W",
                    "W...W....G....W....W",
                    "W.W.WWWWWWWWW.W.WW.W",
                    "W.W.....W.....W..W.W",
                    "W.WWWWW.W.WWW.WW.W.W",
                    "W.....W...W...W..W.W",
                    "WWWWW.WWWWW.W.W.WW.W",
                    "W.....W.G...W.W....W",
                    "W.WWW.W.WWWWW.WWWW.W",
                    "W...W.........D....W",
                    "W.W.WWWWW.WWWWWWWW.W",
                    "W.W.....W.W........W",
                    "W.WWWWW.W.W.WWWWWW.W",
                    "W.W.....W.W....G...W",
                    "W.W.WWW.W.WWWWWWWW.W",
                    "W...W.K.W..........W",
                    "WWWWW.W.WWWWWWWWWW.W",
                    "W.....W........G...W",
                    "WWWWWWWWWWWWWWWWWWWW"
            },

            // Niveau 4 : Symétrique et difficile
            {
                    "WWWWWWWWWWWWWWWWWWWWW",
                    "W.........P.........W",
                    "W.WWWWW.WWWWW.WWWW.W",
                    "W.W.....W...W....W.W",
                    "W.W.WWWWW.W.WWWW.W.W",
                    "W...W.....W.....W..W",
                    "WWW.W.WWW.W.WWW.W.WW",
                    "W...W.W.G.W.G.W.W..W",
                    "W.WWW.W.WWWWW.W.WW.W",
                    "W..K..W...D...W....W",
                    "W.WWW.WWWWWWWWW.WW.W",
                    "W.....W...G...W....W",
                    "W.WWW.W.WWWWW.W.WW.W",
                    "W...W.W.G.W.G.W.W..W",
                    "WWW.W.WWW.W.WWW.W.WW",
                    "W...W.....W.....W..W",
                    "W.W.WWWWW.W.WWWW.W.W",
                    "W.W.....W...W....W.W",
                    "W.WWWWW.WWWWW.WWWW.W",
                    "W...................W",
                    "WWWWWWWWWWWWWWWWWWWW"
            }
    };

    public GameBoard(int rows, int cols, int level) {
        board = new Field[BOARD_SIZE][BOARD_SIZE];
        ghosts = new ArrayList<>();
        score = 0;
        currentLevel = Math.min(Math.max(1, level), LEVELS.length);
        pacmanDirection = Direction.RIGHT;
        gameRunning = true;
        initializeBoard();
    }

    private void initializeBoard() {
        // Utilise le niveau actuel
        String[] currentLevel = LEVELS[this.currentLevel - 1];

        for (int row = 0; row < BOARD_SIZE; row++) {
            String rowString = currentLevel[row];
            for (int col = 0; col < BOARD_SIZE; col++) {
                char c = rowString.charAt(col);
                initializeCell(row, col, c);
            }
        }
    }

    private void initializeCell(int row, int col, char c) {
        switch (c) {
            case 'W': // Mur
                board[row][col] = new Field(FieldType.WALL, row, col);
                break;

            case '.': // Point/Pièce à collecter
                board[row][col] = new Field(FieldType.COIN, row, col);
                break;

            case 'P': // Position initiale de Pacman
                board[row][col] = new Field(FieldType.EMPTY, row, col);
                pacman = new Pacman(row, col);
                pacmanDirection = Direction.RIGHT; // Direction initiale
                break;

            case 'G': // Fantôme
                board[row][col] = new Field(FieldType.EMPTY, row, col);
                Ghost ghost = new Ghost(row, col);
                // Direction aléatoire initiale pour le fantôme
                ghost.setDirection(Direction.values()[(int)(Math.random() * Direction.values().length)]);
                ghosts.add(ghost);
                break;

            case 'K': // Clé
                board[row][col] = new Field(FieldType.KEY, row, col);
                break;

            case 'D': // Porte/Gate
                board[row][col] = new Field(FieldType.GATE, row, col);
                break;

            case '-': // Mur spécial pour la zone des fantômes
                board[row][col] = new Field(FieldType.WALL, row, col);
                break;

            default:  // Par défaut, case vide
                board[row][col] = new Field(FieldType.EMPTY, row, col);
                break;
        }
    }

    // Méthode pour passer au niveau suivant
    public boolean goToNextLevel() {
        if (currentLevel < LEVELS.length) {
            currentLevel++;
            ghosts.clear();
            initializeBoard();
            return true;
        }
        return false;
    }

    private void handleFieldEffect(Field field) {
        if (field == null) return;

        switch (field.getType()) {
            case COIN:
                SoundManager.playChomp();
                score += 10;
                field.setType(FieldType.EMPTY);
                break;
            case KEY:
                SoundManager.playPowerUp();
                pacman.setHasKey(true);
                field.setType(FieldType.EMPTY);
                break;
            case GATE:
                if (pacman.hasKey()) {
                    SoundManager.playFruit();
                    if (goToNextLevel()) {
                        score += 50;
                    } else {
                        gameRunning = false;
                        score += 100;
                    }
                }
                break;
        }
    }

    private void checkCollisions() {
        for (Ghost ghost : ghosts) {
            if (ghost.getRow() == pacman.getRow() && ghost.getCol() == pacman.getCol()) {
                SoundManager.playDeath();
                gameRunning = false;
                return;
            }
        }
    }

    public void update(long now) {
        if (!gameRunning) return;

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastGhostMove >= GHOST_MOVE_DELAY) {
            for (Ghost ghost : ghosts) {
                moveGhost(ghost);
            }
            lastGhostMove = currentTime;
        }

        // Vérifier les collisions
        checkCollisions();
    }

    public void movePacman(Direction dir) {
        if (!gameRunning) return;

        pacmanDirection = dir;
        int newRow = pacman.getRow();
        int newCol = pacman.getCol();

        switch (dir) {
            case UP:    newRow--; break;
            case DOWN:  newRow++; break;
            case LEFT:  newCol--; break;
            case RIGHT: newCol++; break;
        }

        if (isValidMove(newRow, newCol)) {
            pacman.setRow(newRow);
            pacman.setCol(newCol);
            handleFieldEffect(board[newRow][newCol]);
        }
    }

    private void moveGhost(Ghost ghost) {
        if (canGhostMove(ghost, ghost.getDirection())) {
            ghost.setDirection(getRandomValidDirection(ghost));
        }

        Direction dir = ghost.getDirection();
        int newRow = ghost.getRow();
        int newCol = ghost.getCol();

        switch (dir) {
            case UP:    newRow--; break;
            case DOWN:  newRow++; break;
            case LEFT:  newCol--; break;
            case RIGHT: newCol++; break;
        }

        if (isValidMove(newRow, newCol)) {
            ghost.setRow(newRow);
            ghost.setCol(newCol);
        }
    }

    private boolean canGhostMove(Ghost ghost, Direction dir) {
        int newRow = ghost.getRow();
        int newCol = ghost.getCol();

        switch (dir) {
            case UP:    newRow--; break;
            case DOWN:  newRow++; break;
            case LEFT:  newCol--; break;
            case RIGHT: newCol++; break;
        }

        return !isValidMove(newRow, newCol);
    }

    private Direction getRandomValidDirection(Ghost ghost) {
        Direction[] directions = Direction.values();
        Direction newDir;
        do {
            newDir = directions[(int)(Math.random() * directions.length)];
        } while (canGhostMove(ghost, newDir));
        return newDir;
    }

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= BOARD_SIZE || col < 0 || col >= BOARD_SIZE) {
            return false;
        }

        Field field = board[row][col];
        if (field.getType() == FieldType.WALL) {
            return false;
        }

        return field.getType() != FieldType.GATE || pacman.hasKey();
    }

    public void draw(GraphicsContext gc) {
        // Effacer le canvas
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, BOARD_SIZE * CELL_SIZE, BOARD_SIZE * CELL_SIZE);

        // Dessiner le plateau
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                drawField(gc, row, col);
            }
        }

        // Dessiner Pacman
        gc.setFill(Color.YELLOW);
        gc.fillOval(pacman.getCol() * CELL_SIZE,
                pacman.getRow() * CELL_SIZE,
                CELL_SIZE - 2, CELL_SIZE - 2);

        // Dessiner les fantômes
        gc.setFill(Color.RED);
        for (Ghost ghost : ghosts) {
            gc.fillOval(ghost.getCol() * CELL_SIZE,
                    ghost.getRow() * CELL_SIZE,
                    CELL_SIZE - 2, CELL_SIZE - 2);
        }
    }

    private void drawField(GraphicsContext gc, int row, int col) {
        double x = col * CELL_SIZE;
        double y = row * CELL_SIZE;

        switch (board[row][col].getType()) {
            case WALL:
                gc.setFill(Color.BLUE);
                gc.fillRect(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4);
                break;
            case COIN:
                gc.setFill(Color.YELLOW);
                double coinSize = 6;
                gc.fillOval(x + (CELL_SIZE - coinSize)/2,
                        y + (CELL_SIZE - coinSize)/2,
                        coinSize, coinSize);
                break;
            case KEY:
                gc.setFill(Color.GREEN);
                double keySize = 12;
                gc.fillOval(x + (CELL_SIZE - keySize)/2,
                        y + (CELL_SIZE - keySize)/2,
                        keySize, keySize);
                break;
            case GATE:
                gc.setFill(Color.RED);
                gc.fillRect(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4);
                break;
        }
    }


    public int getScore() { return score; }
    public int getCurrentLevel() { return currentLevel; }
    public boolean isGameRunning() { return gameRunning; }
    public int getBoardPixelSize() {
        return BOARD_SIZE * CELL_SIZE;
    }

}