package ru.taratorkin.TestApp.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Conversion{

    public static List<String> conversionOperand(String operand) {
        return Arrays.asList(operand.split(", ", -1));
    }

    public static int[][] conversionMatrix(String matrix) {
        List<String> rows =
                Arrays.stream(matrix.split("[\\[\\]]+", -1)).map(String::trim)
                        .filter(o -> !o.equals("")).collect(Collectors.toList());

        int[][] result = new int[rows.size()][rows.size()];
        int i = -1, j = -1;
        for (String row: rows) {
            i += 1;
            List<String> cells = Arrays.stream(row.split(", ", -1)).map(String::trim)
                    .collect(Collectors.toList());
            for (String cell : cells) {
                j += 1;
                result[i][j] = Integer.parseInt(cell);
            }
            j = -1;
        }
        return result;
    }

}
