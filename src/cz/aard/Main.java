package cz.aard;

public class Main {

    public static void main(String[] args) {
        Sudoku sudoku = new Sudoku();
        Solver solver = new Solver(sudoku);

        // OK
        sudoku.load(""
                + "_1_____96"
                + "65___2___"
                + "__4_5_2__"
                + "8__1____5"
                + "_6_____3_"
                + "2____4__1"
                + "__8_3_4__"
                + "___5___29"
                + "59_____6_");

        solver.solve();
        System.out.println("Check: " + sudoku.check());
        System.out.println(sudoku);

        // OK
        sudoku.load(""
                + "____842__"
                + "4__1__8_9"
                + "__1_6_3_4"
                + "_8__914__"
                + "27_____86"
                + "__467__2_"
                + "3_5_2_6__"
                + "1_8__5__2"
                + "__241____");

        solver.solve();
        System.out.println("Check: " + sudoku.check());
        System.out.println(sudoku);

        // OK
        sudoku.load(""
                + "____7_5_4"
                + "___2_6__9"
                + "7_____3_6"
                + "95___12__"
                + "_________"
                + "__84___61"
                + "2_4_____7"
                + "5__7_3___"
                + "1_6_5____");


        solver.solve();
        System.out.println("Check: " + sudoku.check());
        System.out.println(sudoku);

        // OH - superhard
        sudoku.load(""
                + "_________"
                + "_____3_85"
                + "__1_2____"
                + "___5_7___"
                + "__4___1__"
                + "_9_______"
                + "5______73"
                + "__2_1____"
                + "____4___9");

        solver.solve();
        System.out.println("Check: " + sudoku.check());
        System.out.println(sudoku);
    }
}
