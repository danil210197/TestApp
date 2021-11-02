package ru.taratorkin.TestApp.model;

public class SolverFindSubstringsFactory implements SolverFactory{
    @Override
    public Solver createSolver(Task task) {
        return new SolverFindSubstrings(task);
    }
}
