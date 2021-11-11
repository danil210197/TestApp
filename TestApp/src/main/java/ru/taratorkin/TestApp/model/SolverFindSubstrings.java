package ru.taratorkin.TestApp.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SolverFindSubstrings extends Solver {

    public SolverFindSubstrings(Task task) {
        super(task);
    }

    @Override
    public String getResult() {
        Task task = this.getTask();
        List<String> operandFirst = task.getOperandFirst();
        List<String> operandSecond = task.getOperandSecond();
        List<String> result = new ArrayList<>();

        for (String op1 : operandFirst) {
            for (String op2 : operandSecond) {
                if (op2.contains(op1) && !result.contains(op1)) {
                    result.add(op1);
                }
            }
        }

        return Arrays.toString(result.toArray());
    }

}
