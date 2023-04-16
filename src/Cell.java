
import javax.swing.*;
import java.awt.*;

public class Cell {
    private int row;
    private int col;
    private boolean mine;
    private boolean shown;
    private int surroundingMines;

    public Cell(int row, int col) {
        this.row=row;
        this.col=col;
        this.mine=false;
        this.shown=false;
        this.surroundingMines=0 ;
    }


}
