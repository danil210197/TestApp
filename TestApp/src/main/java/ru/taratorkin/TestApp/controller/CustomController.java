package ru.taratorkin.TestApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.taratorkin.TestApp.model.*;
import ru.taratorkin.TestApp.service.TaskService;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;


@Controller
public class CustomController {

    @Autowired
    private TaskService  taskService;


    @GetMapping("/")
    public String getIndex(Model model) {

        model.addAttribute("Task", new Task());
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="solve")
    public String solve(Model model, @ModelAttribute("Task") Task task) {

        Solver solver = SolverFactory.getSolverFactory(task.getType()).createSolver(task);
        model.addAttribute("solution", solver.getResult());
        model.addAttribute("Task", task);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="save")
    public String save(Model model, @ModelAttribute("Task") Task task) {

        taskService.add(task);
        model.addAttribute("Task", task);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="display")
    public String display(Model model, @ModelAttribute("Task") Task task,
                          @RequestParam("selectTaskId") String taskId) {

        Task selectTask = taskService.getByTaskId(Long.parseLong(taskId));
        model.addAttribute("Task", selectTask);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="import")
    public String importData(Model model, @RequestParam("file") MultipartFile file,
                             @ModelAttribute("Task") Task task) throws IOException {

        Task importTask = taskService.importFromFile(file.getInputStream());
        taskService.add(importTask);
        model.addAttribute("Task", importTask);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="export")
    public String export(Model model, @ModelAttribute("Task") Task task) {

        return "index";
    }
}
