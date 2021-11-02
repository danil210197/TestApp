package ru.taratorkin.TestApp.service;


import ru.taratorkin.TestApp.model.Task;

import java.util.List;

public interface TaskService {

    Task add(Task task);
    void deleteById(Long id);
    Task update(Task task);
    List<Task> findAll();
    Task create();
    Task getById(long id);
}
