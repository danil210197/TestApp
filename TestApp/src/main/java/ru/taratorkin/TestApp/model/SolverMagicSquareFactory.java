package ru.taratorkin.TestApp.model;

public class SolverMagicSquareFactory implements SolverFactory {

    @Override
    public Solver createSolver(Task task) {
        return new SolverMagicSquare(task);
    }
}
