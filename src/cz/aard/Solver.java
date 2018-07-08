package cz.aard;

public class Solver {

    public Sudoku sudoku;

    public Solver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public boolean solve() {
        Cell best = sudoku.getBestCell();

        if (best == null) {
            return sudoku.check();
        }

        for (int value : best.options) {
            if (sudoku.check(best.row, best.col, value)) {
                sudoku.cells.get(best.row).get(best.col).value = value;

//                System.out.println("Solving [" + (char) (best.row + 65) + ", " + (best.col + 1) + "] with: " + best.options);
//                System.out.println("Trying " + value);
//                System.out.println(sudoku);

                boolean ok = solve();
                if (ok == true) {
                    return true;
                }

//                System.out.println("Nope [" + (char) (best.row + 65) + ", " + (best.col + 1) + "] for: " + value);

                sudoku.cells.get(best.row).get(best.col).value = 0;
            }
        }

        return false;
    }
}
