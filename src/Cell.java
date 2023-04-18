
import javax.swing.*;
import java.awt.*;

public class Cell {
    private int row;
    private int col;
    private boolean mine;
    private boolean shown;
    private int surroundingMines;


    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        this.mine = false;
        this.shown = false;
        this.surroundingMines = 0;
    }
    {
    public int getRow(){return row;}
    public int getCol(){return col;}
    public boolean hasMine(){ return mine; }
    public void setMine(boolean mine){this.mine = mine;}
    public boolean isShown(){ return shown; }
    public void setShown(boolean shown){ this.shown = shown;}
    public int getSurroundingMines(){return surroundingMines;}
    public void setSurroundingMines(int surroundingMines){this.surroundingMines = surroundingMines;}

    }
}


