package ru.taratorkin.TestApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.taratorkin.TestApp.model.*;
import ru.taratorkin.TestApp.util.*;
import ru.taratorkin.TestApp.service.TaskService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Controller
public class CustomController {

    private static final String DEFAULT_FILE_NAME = "Tasktmp.txt";

    @Autowired
    private TaskService taskService;

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/")
    public String getIndex(Model model) {

        model.addAttribute("Task", new Task());
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value = "/click", method = RequestMethod.POST, params = "solve")
    public String solve(Model model, @ModelAttribute("Task") Task task) {

        Solver solver = SolverFactory.getSolverFactory(task.getType()).createSolver(task);
        model.addAttribute("solution", solver.getResult());
        model.addAttribute("Task", task);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value = "/click", method = RequestMethod.POST, params = "save")
    public String save(Model model, @ModelAttribute("Task") Task task) {

        taskService.add(task);
        model.addAttribute("Task", task);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value = "/click", method = RequestMethod.POST, params = "display")
    public String display(Model model, @RequestParam("selectTaskId") String taskId) {

        Task selectTask = taskService.getByTaskId(Long.parseLong(taskId));
        model.addAttribute("Task", selectTask);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value = "/click", method = RequestMethod.POST, params = "import")
    public String importData(Model model, @RequestParam("importFile") MultipartFile file) throws IOException {

        Task importTask = taskService.importFromFile(file.getInputStream());
        taskService.add(importTask);
        model.addAttribute("Task", importTask);
        model.addAttribute("tasks", taskService.findAll());
        return "index";
    }

    @RequestMapping(value = "/click", method = RequestMethod.POST, params = "export")
    public void export(HttpServletResponse httpServletResponse,
                       @ModelAttribute("Task") Task task) throws IOException {

        MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, DEFAULT_FILE_NAME);
        httpServletResponse.setContentType(mediaType.getType());
        httpServletResponse.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "exportFile.txt");
        taskService.exportToFile(task, httpServletResponse.getOutputStream());
    }

}
