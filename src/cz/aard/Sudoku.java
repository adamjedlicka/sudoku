package cz.aard;

import java.util.ArrayList;

public class Sudoku {

    public int dimension = 9;

    public ArrayList<ArrayList<Cell>> cells;

    public Sudoku() {
        init();
    }

    public Sudoku(Sudoku old) {
        this.dimension = old.dimension;
        this.cells = new ArrayList<>(old.cells);
    }

    public void load(String data) {
        if (data.length() != dimension * dimension) {
            throw new IllegalArgumentException(
                    String.format("Bad length of data. Expected: %d, got: %d.",
                            dimension * dimension, data.length())
            );
        }

        init();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                char c = data.charAt(i * dimension + j);
                if (c != '_') {
                    cells.get(i).set(j, new Cell(i, j, Character.getNumericValue(c), true));
                }
            }
        }
    }

    private void init() {
        cells = new ArrayList<>();

        for (int i = 0; i < dimension; i++) {
            cells.add(new ArrayList<>());

            for (int j = 0; j < dimension; j++) {
                cells.get(i).add(new Cell(i, j, 0, false));
            }
        }
    }

    public Cell getBestCell() {
        Cell cell = null;
        int min = dimension + 1;

        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Cell tmp = cells.get(row).get(col);
                if (tmp.isFixed == false && tmp.value == 0) {
                    tmp.options = getOptions(row, col);

                    if (tmp.options.size() < min && tmp.options.size() > 0) {
                        min = tmp.options.size();
                        cell = new Cell(tmp);
                    }
                }
            }
        }

        return cell;
    }

    public ArrayList<Integer> getOptions(int row, int col) {
        ArrayList<Integer> arr = new ArrayList<>();

        if (cells.get(row).get(col).value != 0) {
            return arr;
        }

        for (int value = 1; value <= dimension; value++) {
            if (check(row, col, value) == true) {
                arr.add(value);
            }
        }

        return arr;
    }

    public boolean check() {
        for (int row = 0; row < dimension; row++) {
            for (int col = 0; col < dimension; col++) {
                Cell cell = cells.get(row).get(col);
                if (cell.isFixed == true) {
                    continue;
                }

                int value = cell.value;
                cell.value = 0;

                boolean ok = check(row, col, value);

                cell.value = value;

                if (ok == false) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean check(int row, int col, int value) {
        return checkRow(row, value) && checkCol(col, value) && checkSquare(row, col, value);
    }

    public boolean checkRow(int row, int value) {
        return check(row, 0, row + 1, dimension, value);
    }

    public boolean checkCol(int col, int value) {
        return check(0, col, dimension, col + 1, value);
    }

    public boolean checkSquare(int row, int col, int value) {
        int rowFrom = (row / 3) * 3;
        int rowTo = rowFrom + 3;
        int colFrom = (col / 3) * 3;
        int colTo = colFrom + 3;

        return check(rowFrom, colFrom, rowTo, colTo, value);
    }

    public boolean check(int rowFrom, int colFrom, int rowTo, int colTo, int value) {
        for (int row = rowFrom; row < rowTo; row++) {
            for (int col = colFrom; col < colTo; col++) {
                if (cells.get(row).get(col).value == value) {
                    return false;
                }
            }
        }

        return true;
    }

    public void print() {
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                cells.get(i).get(j).print();
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("    ");
        for (int i = 0; i < dimension; i++) {
            sb.append((i + 1) + " ");
        }
        sb.append('\n');
        sb.append("    ");
        for (int i = 0; i < dimension; i++) {
            sb.append("--");
        }
        sb.append('\n');

        for (int i = 0; i < dimension; i++) {
            sb.append((char) (i + 65) + " | ");

            for (int j = 0; j < dimension; j++) {
                sb.append(cells.get(i).get(j));
                sb.append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}
