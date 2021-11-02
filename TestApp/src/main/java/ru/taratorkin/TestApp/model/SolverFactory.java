package ru.taratorkin.TestApp.model;

public interface SolverFactory {

    Solver createSolver(Task task);

    static SolverFactory getSolverFactory(String type){

        if(type.equals(TypeTask.STRINGS.toString()))
            return new SolverFindSubstringsFactory();
        else if(type.equals(TypeTask.MAGIC_SQUARE.toString()))
            return new SolverMagicSquareFactory();

        return null;
    }
}
