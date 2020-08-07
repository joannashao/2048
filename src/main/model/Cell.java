package model;

// Represents a cell of the game board with a row number and column number
public class Cell {
    private int column;
    private int row;


    public Cell(int c, int r) {
        this.column = c;
        this.row = r;
    }

    public int getColumn() {
        return this.column;
    }

    public int getRow() {
        return this.row;
    }

}
