
import javax.swing.*;
import java.awt.*;

public enum DifficultyLevel {
    EASY(10, 10, 10),
    MEDIUM(16, 16, 40),
    HARD(25, 25, 150);

    private final int rows;
    private final int cols;
    private final int mines;

    DifficultyLevel(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMines() {
        return mines;
    }
}

