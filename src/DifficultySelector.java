import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class DifficultySelector extends JDialog {
    private DifficultySelector selectedDifficulty;

    public DifficultySelector(JFrame parent){
        super(parent, "Select difficulty", true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        JButton rookieButton = new JButton("rookie");
        JButton experiencedButton = new JButton("experienced");
        JButton VetButton = new JButton("Vet");


    }
}
