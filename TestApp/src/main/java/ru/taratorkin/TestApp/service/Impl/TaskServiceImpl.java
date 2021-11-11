package ru.taratorkin.TestApp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.taratorkin.TestApp.model.ImportExportFile;
import ru.taratorkin.TestApp.model.Task;
import ru.taratorkin.TestApp.model.TypeTask;
import ru.taratorkin.TestApp.repository.TaskRepository;
import ru.taratorkin.TestApp.service.TaskService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    private ImportExportFile importExportFile;

    @Override
    public Task add(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task update(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task create() {
        return null;
    }

    @Override
    public Task getByTaskId(long id) {
        Task task = taskRepository.getByTaskId(id);
        if (task.getType().equals(TypeTask.MAGIC_SQUARE.toString())) {
            task.setMatrixString(task.getMatrixString());
        }
        return task;
    }

    @Override
    public void exportToFile(Task task, OutputStream outputStream) throws IOException {
        new ImportExportFile(outputStream).writeToFile(task);
    }

    @Override
    public Task importFromFile(InputStream inputStream) throws IOException {
        return new ImportExportFile(inputStream).readFromFile();
    }

}
