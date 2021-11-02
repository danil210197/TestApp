package ru.taratorkin.TestApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.taratorkin.TestApp.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}
