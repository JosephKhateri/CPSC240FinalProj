import javax.swing.*;
import java.awt.*;

public class Minesweeper extends JFrame {
    private final int ROWS = 10;
    private final int COLS = 10;
    private final int MINE_COUNT = 10;
    private JButton[][] buttons;
    private boolean[][] mines;
    private boolean[][] shownmines;
    private int[][] surroundCount;
    public Minesweeper() {
        setTitle("Minesweeper");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel boardPanel = new JPanel(new GridLayout(10, 10));

        for (int i = 0; i < 100; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(50, 50));
            boardPanel.add(button);
        }

        add(boardPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        Minesweeper minesweeper = new Minesweeper();
    }


}

