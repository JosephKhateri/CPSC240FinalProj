import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DifficultySelector extends JDialog {
    private DifficultyLevel selectedDifficulty;

    /**
     * Constructs a new Difficulty Selector dialog with the parent JFrame
     * @param parent The parent JFrame of the dialog
     */

    public DifficultySelector(JFrame parent){
        super(parent, "Select difficulty", true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Experienced");
        JButton hardButton = new JButton("Vet");

        //Gives easy button actionlistener
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDifficulty = DifficultyLevel.EASY;
                dispose();

            }
        });
        //Gives medium button actionlistener
        mediumButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDifficulty = DifficultyLevel.MEDIUM;
                dispose();
            }
        });
        //Gives hard button actionlistener
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedDifficulty = DifficultyLevel.HARD;
                dispose();
            }
        });
        // Adds three buttons
        add(easyButton);
        add(mediumButton);
        add(hardButton);
        pack();


    }

    /**
     * Gets the difficulty level selected
     * @return the difficulty level selected
     */
    public DifficultyLevel getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
