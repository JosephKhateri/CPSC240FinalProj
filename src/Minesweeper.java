import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
/**
 * Minesweeper game a graphical user interface.
 *
 * @author Joseph, Eric, Keegan
 */
public class Minesweeper extends JFrame {
    private int rows;
    private int cols;
    private int mineCount;
    private JButton[][] buttons;
    private Cell[][] board;

    /**
     * Constructs a new Minesweeper game with a specified difficulty level.
     *
     * @param difficulty The difficulty level for the game.
     */
    public Minesweeper(DifficultyLevel difficulty) {
        this.rows = difficulty.getRows();
        this.cols = difficulty.getCols();
        this.mineCount = difficulty.getMines();
        setTitle("Minesweeper");
        setSize(cols * 50 + 16, rows * 50 + 39);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttons = new JButton[rows][cols];
        board = new Cell[rows][cols];

        // Initialize the board cells
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                board[row][col] = new Cell(row,col);
            }
        }

        setMine();
        adjacentMines();
        // Create the board panel
        JPanel boardPanel = new JPanel(new GridLayout(rows, cols));
        // Create and add buttons to the panel
            for (int row = 0; row < rows; row++) {
                for( int col = 0; col<cols; col++){
                    // Create a new button and set its properties
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(50,50));
                    final int r = row;
                    final int c = col;
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!(board[r][c].isFlag())){
                                clickCell(r,c);
                            }
                        }
                    });
                    button.addMouseListener(new java.awt.event.MouseAdapter(){
                        @Override
                        public void mouseClicked(java.awt.event.MouseEvent e) {
                            if(SwingUtilities.isRightMouseButton(e)){
                                board[r][c].setFlag(!board[r][c].isFlag());
                                if(board[r][c].isFlag()){
                                    button.setText("F");
                                } else {
                                    button.setText("");
                                }

                            }
                        }
                    });
                    buttons[row][col]=button;
                    boardPanel.add(button);
                }

        }

        add(boardPanel);
        setVisible(true);
    }

    /**
     * Handles a cell click, revealing the cell or ending the game.
     *
     * @param row The row index of the clicked cell.
     * @param col The column index of the clicked cell.
     */
    private void clickCell(int row, int col) {
        if(board[row][col].isMine()){
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

    /**
     * Randomly places the mines on the board.
     */
    private void setMine() {
        int minesPlaced = 0;
        while (minesPlaced < mineCount) {
            int row = (int) (Math.random() * rows);
            int col = (int) (Math.random() * cols);
            if (!board[row][col].isMine()) {
                board[row][col].setMine(true);
                minesPlaced++;
            }
        }
    }

    /**
     * Calculates the number of adjacent mines for each cell on the board.
     */
    private void adjacentMines() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col].isMine()) continue;
                int count = 0;
                for (int r = row - 1; r <= row + 1; r++) {
                    for (int c = col - 1; c <= col + 1; c++) {
                        if (r < 0 || r >= rows || c < 0 || c >= cols) continue;
                        if (board[r][c].isMine()) count++;
                    }
                }
                board[row][col].setSurroundingMines(count);
            }
        }
    }

    /**
     * Uncover a cell on the board and reveals its content.
     * If the cell has no adjacent mines, it uncovers its neighbors recursively.
     *
     * @param row The row index of the cell to uncover.
     * @param col The column index of the cell to uncover.
     */
    private void uncovered(int row, int col) {
        if(board[row][col].isShown()&&board[row][col].isMine()) return;
        board[row][col].setShown(true);
        buttons[row][col].setEnabled(false);
        buttons[row][col].setText(Integer.toString(board[row][col].getSurroundingMines()));
        if (board[row] [col].getSurroundingMines() == 0){
            for (int r = row - 1; r<= row + 1; r++){
                for (int c = col - 1; c <= col + 1; c++){
                    if (r < 0 || r >= rows || c < 0 || c >= cols) continue;
                    if (!board[r][c].isMine() && !board[r][c].isShown())
                    uncovered(r, c);
                }
            }
        }
    }

    /**
     * Checks if the game has been won by the player.
     *
     * @return true if the game is won, false otherwise.
     */
    private boolean isGameWon() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (!board[row][col].isMine() && !board[row][col].isShown()) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * The main method to run the Minesweeper game.
     *
     * @param args Unused command-line arguments.
     */
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



