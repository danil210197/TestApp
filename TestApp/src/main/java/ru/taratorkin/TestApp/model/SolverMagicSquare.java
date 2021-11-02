package ru.taratorkin.TestApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class SolverMagicSquare extends Solver {

    private int[][] inputMatrix;

    public SolverMagicSquare(Task task) {
        super(task);
    }

    @Override
    public String getResult() {

        inputMatrix = this.getTask().getMatrix();
        FindMagicMatrix magicSquare = new FindMagicMatrix(3);
        List<Matrix> matrixList = magicSquare.findAllMagicMatrix(0);
        Collections.sort(matrixList);
        Matrix matrix = matrixList.get(1);

        return getSolutionString(matrix);
    }

    private String getSolutionString(Matrix matrix){

        String solution = String.format("Стоимость превращения: %s", matrix.getCostConversion());
        int[][] resMatrix = matrix.getMatrix();

        for(int i = 0; i < resMatrix.length; i++){
            solution += "\n";
            for(int j = 0; j < resMatrix.length; j++){
                solution += resMatrix[i][j] + "  ";
            }
        }

     return solution;
    }


    @AllArgsConstructor
    @Getter
    private class Matrix implements Comparable<Matrix>{

        private Integer costConversion;
        private int[][] matrix = new int[3][3];

        @Override
        public int compareTo(Matrix otherMatrix) {
            return this.getCostConversion().compareTo(otherMatrix.getCostConversion());
        }

        public Matrix(int[][] matrix) {
            this.matrix = copyMatrix(matrix);
            this.costConversion = calcCostConversion(matrix);
        }

        private Integer calcCostConversion(int[][] matrix){
            Integer result = 0;
            for (int i = 0; i < matrix.length; i++){
                for (int j = 0; j < matrix.length; j++){
                    result += Math.abs(inputMatrix[i][j] - matrix[i][j]);
                }
            }
            return result;
        }

        private int[][] copyMatrix(int[][] matrix){
            int[][] result = new int[matrix.length][matrix.length];
            for(int i = 0; i < matrix.length; i++){
                for(int j = 0; j < matrix.length; j++){

                   result[i][j] = matrix[i][j];
                }
            }
            return result;
        }
    }

    @Getter
    @Setter
    private class FindMagicMatrix {

        private int[][] square;
        private boolean[] used;
        private int n;
        private int magicSum;
        private int total;
        private List<Matrix> matrixList;

        public FindMagicMatrix(int n) {

            matrixList = new ArrayList<>();
            square = new int[n][n];
            int k = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    square[i][j] = ++k;
            }
            used = new boolean[n * n + 1];

            for (int i = 0; i < n * n + 1; i++) {
                used[i] = false;
            }
            this.n = n;
            this.total = 0;
            this.magicSum = n * (n * n + 1) / 2;
        }

        private boolean isValid() {
            int sumD1 = 0;
            int sumD2 = 0;

            for (int i = 0; i < n; i++) {
                int sumR = 0;
                int sumC = 0;
                for (int j = 0; j < n; j++) {
                    sumR += square[i][j];
                    sumC += square[j][i];
                }
                if (sumR != magicSum || sumC != magicSum) {
                    return false;
                }
            }
            return true;
        }

        private List<Matrix> findAllMagicMatrix(int step) { //подсчитать с выводом
            if (step == n * n) {
                if (isValid()) {
                      total++;
                      matrixList.add(new Matrix(square));
                }
                return matrixList;
            }
            for (int val = 1; val <= n * n; val++) {
                if (used[val]) {
                    continue;
                }

                used[val] = true;
                square[step / n][step % n] = val;
                findAllMagicMatrix(step + 1);
                square[step / n][step % n] = 0;
                used[val] = false;
            }
            return matrixList;
        }

        // вывод решении
        void outputSolution() {
            System.out.printf("#%s", total);
            for (int r = 0; r < n; r++) {
                System.out.println();
                for (int c = 0; c < n; c++) {
                    System.out.printf("%s ", square[r][c]);
                }
            }
            System.out.println();
        }

    }

}
