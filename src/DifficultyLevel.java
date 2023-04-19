
import javax.swing.*;
import java.awt.*;

// Telling each level sizes

public enum DifficultyLevel {
    /**
     * Easy difficulty level 10 by 10 by 10
     */
    EASY(10, 10, 10),
    /**
     * Medium difficulty Level 16 by 16 by 16
     */
    MEDIUM(16, 16, 40),
    /**
     * Hard difficulty level 25 by 25 by 25
     */
    HARD(25, 25, 150);

    private final int rows;
    private final int cols;
    private final int mines;

    /**
     * Constructs the difficulty level enumeration with the amount of number of rows, columns, and mines
     * @param rows the number of rows on the grid
     * @param cols the number of columns on the grid
     * @param mines the number of mines in the grid
     */

    DifficultyLevel(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;
    }

    /**
     * Returns the number of rows on the grid for the difficuly level
     * @return the number of rows on the grid
     */
    public int getRows() {
        return rows;
    }

    /**
     * Returns the number of columns in the grid for the difficulty
     * @return the number of columns on the grid
     */
    public int getCols() {
        return cols;
    }

    /**
     * Returns the number of mines in the grid for the difficulty
     * @return the number of mines in the grid.
     */
    public int getMines() {
        return mines;
    }
}

