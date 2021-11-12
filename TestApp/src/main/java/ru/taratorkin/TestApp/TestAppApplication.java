package ru.taratorkin.TestApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TestAppApplication.class, args);

	}
}
