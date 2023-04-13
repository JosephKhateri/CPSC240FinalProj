import javax.swing.*;
import java.awt.*;

public class Minesweeper extends JFrame {
    private final int ROWS = 10;
    private final int COLS = 10;
    private final int MINE_COUNT = 10;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] shownMines;
    private int[][] surroundCount;

    public Minesweeper() {
        setTitle("Minesweeper");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttons = new JButton[ROWS][COLS];
        mines = new boolean[ROWS][COLS];
        shownMines = new boolean[ROWS][COLS];
        surroundCount = new int[ROWS][COLS];


        JPanel boardPanel = new JPanel(new GridLayout(10, 10));

        for (int i = 0; i < 100; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            boardPanel.add(button);
        }

        add(boardPanel);
        setVisible(true);
    }

    setMine();
    AdjacentMines();

    private void clickCell(JButton Button) {

    }

    private void setMine(){
        int minesPlaced = 0;
        while (minesPlaced < MINE_COUNT){
            int row = (int) (Math.random() * ROWS);
            int col = (int) (Math.random() * COLS);
            if (!mines[row][col]){
                mines[row][col]=true;
                minesPlaced++;
            }
        }
    }

    private void AdjacentMines() {
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
                surroundCount[row][col] = count;
            }
        }
    }

    }

    private void uncovered() {

    }

    private boolean isGameWon() {

    }


    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
    }


}

