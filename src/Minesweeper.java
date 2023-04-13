import javax.swing.*;
import java.awt.*;

public class Minesweeper extends JFrame {

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