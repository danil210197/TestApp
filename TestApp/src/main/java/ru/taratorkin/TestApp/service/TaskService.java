package ru.taratorkin.TestApp.service;

import ru.taratorkin.TestApp.model.Task;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public interface TaskService {

    Task add(Task task);
    void deleteById(Long id);
    Task update(Task task);
    List<Task> findAll();
    Task create();
    Task getByTaskId(long id);
    void exportToFile(Task task, OutputStream outputStream) throws IOException;
    Task importFromFile(InputStream inputStream) throws IOException;
}
