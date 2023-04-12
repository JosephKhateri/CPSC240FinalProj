import javax.swing.*;
import java.awt.*;

public class MineSweeperGUI extends JFrame {

    private final int ROWS = 10;
    private final int COLS = 10;
    private final int MINE_COUNT = 10;

    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] revealed;
    private int[][] neighborCounts;

    public MineSweeperGUI() {
        setTitle("Mine Sweeper");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        buttons = new JButton[ROWS][COLS];
        mines = new boolean[ROWS][COLS];
        revealed = new boolean[ROWS][COLS];
        neighborCounts = new int[ROWS][COLS];

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(ROWS, COLS));

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                JButton button = new JButton();
                button.putClientProperty("row", row);
                button.putClientProperty("col", col);
                button.addActionListener(e -> handleButtonClick((JButton) e.getSource()));
                contentPane.add(button);
                buttons[row][col] = button;
            }
        }

        placeMines();
        countNeighbors();
        setVisible(true);
    }

    private void handleButtonClick(JButton button) {
        int row = (int) button.getClientProperty("row");
        int col = (int) button.getClientProperty("col");

        if (mines[row][col]) {
            JOptionPane.showMessageDialog(this, "You clicked on a mine! Game over.");
            System.exit(0);
        }

        revealSquare(row, col);

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Congratulations, you won!");
            System.exit(0);
        }
    }

    private void placeMines() {
        int minesPlaced = 0;
        while (minesPlaced < MINE_COUNT) {
            int row = (int) (Math.random() * ROWS);
            int col = (int) (Math.random() * COLS);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
    }

    private void countNeighbors() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (mines[row][col]) continue;
                int count = 0;
                for (int r = row - 1; r <= row + 1; r++) {
                    for (int c = col - 1; c <= col + 1; c++) {
                        if (r < 0 || r >= ROWS || c < 0 || c >= COLS) continue;
                        if (mines[r][c]) count++;
                    }
                }
                neighborCounts[row][col] = count;
            }
        }
    }

    private void revealSquare(int row, int col) {
        if (revealed[row][col]) return;
        revealed[row][col] = true;
        buttons[row][col].setEnabled(false);
        buttons[row][col].setText(Integer.toString(neighborCounts[row][col]));
        if (neighborCounts[row][col] == 0) {
            for (int r = row - 1; r <= row + 1; r++) {
                for (int c = col - 1; c <= col + 1; c++) {
                    if (r < 0 || r >= ROWS || c < 0 || c >= COLS) continue;
                    if (!mines[r][c] && !revealed[r][c]) {
                        revealSquare(r, c);
                    }
                }
            }
        }
    }

    private boolean checkWin() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (!mines[row][col] && !revealed[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new MineSweeperGUI();
    }
}

