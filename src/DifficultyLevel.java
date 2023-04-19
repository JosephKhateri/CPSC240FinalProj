
import javax.swing.*;
import java.awt.*;

// Telling each level sizes
public enum DifficultyLevel {
    EASY(10, 10, 10),
    MEDIUM(16, 16, 40),
    HARD(16, 30, 99);

    private final int rows;
    private final int cols;
    private final int mines;

    // constructor for the enumeration
    DifficultyLevel(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }
    // rows for the grade
    public int getRows() {
        return rows;
    }
    // columns for the grid
    public int getCols() {
        return cols;
    }
    // number of mines for the grid
    public int getMines() {
        return mines;
    }
}

