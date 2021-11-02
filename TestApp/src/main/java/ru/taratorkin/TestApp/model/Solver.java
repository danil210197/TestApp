package ru.taratorkin.TestApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Solver {

    private Task task;

    public Solver(Task task){
        this.task = task;
    }

    public abstract String getResult();

}
