package ru.taratorkin.TestApp.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.taratorkin.TestApp.model.Task;
import ru.taratorkin.TestApp.repository.TaskRepository;
import ru.taratorkin.TestApp.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

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
    public Task getById(long id) {
        return taskRepository.getById(id);
    }
}
