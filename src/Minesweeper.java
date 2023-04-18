import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Minesweeper extends JFrame {
    private int rows;
    private int cols;
    private int mineCount;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] shownMines;
    private int[][] surroundCount;

    public Minesweeper(DifficultyLevel difficulty) {
        this.rows = difficulty.getRows();
        this.cols = difficulty.getCols();
        this.mineCount = difficulty.getMines();

        setTitle("Minesweeper");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttons = new JButton[rows][cols];
        mines = new boolean[rows][cols];
        shownMines = new boolean[rows][cols];
        surroundCount = new int[rows][cols];

        setMine();
        adjacentMines();

        JPanel boardPanel = new JPanel(new GridLayout(rows, cols));

            for (int row = 0; row < rows; row++) {
                for( int col = 0; col<cols; col++){
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(50,50));
                    final int r = row;
                    final int c = col;
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            clickCell(r,c);
                        }
                    });
                    buttons[row][col]=button;
                    boardPanel.add(button);
                }

        }

        add(boardPanel);
        setVisible(true);
    }

    private void clickCell(int row, int col) {
        if(mines[row][col]){
            JOptionPane.showMessageDialog(this, "Mine hit! Game over :(");
            System.exit(0);
        }else{
            uncovered(row, col);
            if(isGameWon()){
                JOptionPane.showMessageDialog(this, "WHOOO! YOU WON!");
                System.exit(0);
            }
        }

    }

    private void setMine() {
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * cols);
            if (!mines[row][col]) {
                mines[row][col] = true;
                minesPlaced++;
            }
        }
    }

    private void adjacentMines() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (mines[row][col]) continue;
                int count = 0;
                for (int r = row - 1; r <= row + 1; r++) {
                    for (int c = col - 1; c <= col + 1; c++) {
                        if (r < 0 || r >= rows || c < 0 || c >= cols) continue;
                        if (mines[r][c]) count++;
                    }
                }
                surroundCount[row][col] = count;
            }
        }
    }

    private void uncovered(int row, int col) {
        if(shownMines[row] [col]) return;
        shownMines[row][col] = true;
        buttons[row][col].setEnabled(false);
        buttons[row][col].setText(Integer.toString(surroundCount[row][col]));
        if (surroundCount[row] [col] == 0){
            for (int r = row - 1; r<= row + 1; r++){
                for (int c = col - 1; c <= col + 1; c++){
                    if (r<0 || r >= rows || c < 0 || c>= cols) continue;
                    if (!mines[r][c] && !shownMines[r] [c])
                    uncovered(r, c);
                }
            }
        }
    }

    private boolean isGameWon() {
        for (int rows = 0; rows < rows; rows++) {
            for (int cols = 0; cols < cols; cols++) {
                if (!mines[rows][cols] && !shownMines[rows][cols]) {
                    return false;
                }
            }
        }
        return true;
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                DifficultySelector difficultySelector = new DifficultySelector(frame);
                difficultySelector.setVisible(true);
                DifficultyLevel selectedDifficulty = difficultySelector.getSelectedDifficulty();

                if (selectedDifficulty != null) {
                    Minesweeper minesweeper = new Minesweeper(selectedDifficulty);
                } else {
                    System.exit(0);
                }
                }});
            }
        }



