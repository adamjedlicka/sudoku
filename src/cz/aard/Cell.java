package cz.aard;

import java.util.ArrayList;

public class Cell {

    public int row;

    public int col;

    public int value;

    public boolean isFixed;

    public ArrayList<Integer> options;

    public ArrayList<Integer> tries;

    public Cell(int row, int col, int value, boolean isFixed) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.isFixed = isFixed;
        this.options = new ArrayList<>();
        this.tries = new ArrayList<>();
    }

    public Cell(int row, int col, int value) {
        this(row, col, value, false);
    }

    public Cell(Cell old) {
        this.row = old.row;
        this.col = old.col;
        this.value = old.value;
        this.isFixed = old.isFixed;
        this.options = new ArrayList<>(old.options);
        this.tries = new ArrayList<>(old.tries);
    }

    public boolean isNotEmpty() {
        return value != 0;
    }

    public String toString() {
        if (value == 0) {
            return "_";
        }

        return String.valueOf(value);
    }

    public void print() {
        System.out.println("Cell [" + row + ", " + col + "]");
        System.out.println("  => tries: " + tries);

    }
}
