package ru.taratorkin.TestApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.taratorkin.TestApp.model.Task;
import ru.taratorkin.TestApp.model.TypeTask;
import ru.taratorkin.TestApp.service.Impl.TaskServiceImpl;
import ru.taratorkin.TestApp.service.TaskService;

import java.util.List;

@SpringBootApplication
public class TestAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestAppApplication.class, args);

//		TaskService taskService = (TaskService) context.getBean(TaskServiceImpl.class);
//
//		Task task = new Task();
//		task.setLine1("frgtrb, gregtreg, gregtr, egregrt");
//		task.setLine2("frgtr, frgtre");
//		task.setType(TypeTask.STRINGS.toString());
//		taskService.add(task);
//
//		Task task1 = taskService.getById(1);


	}
}
