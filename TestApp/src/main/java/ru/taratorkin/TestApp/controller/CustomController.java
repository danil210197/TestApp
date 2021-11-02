package ru.taratorkin.TestApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.taratorkin.TestApp.model.Solver;
import ru.taratorkin.TestApp.model.SolverFactory;
import ru.taratorkin.TestApp.model.Task;
import ru.taratorkin.TestApp.model.SolverFindSubstrings;
import ru.taratorkin.TestApp.service.TaskService;

@Controller
public class CustomController {

    @Autowired
    private TaskService  taskService;

    @Autowired
    private SolverFindSubstrings workWithStrings;

    @GetMapping("/")
    public String getIndex(Model model) {

        model.addAttribute("Task", new Task());
        return "index";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="solve")
    public String solve(Model model, @ModelAttribute("Task") Task task) {

        Solver solver = SolverFactory.getSolverFactory(task.getType()).createSolver(task);
        model.addAttribute("solution", solver.getResult());
        model.addAttribute("Task", task);
        return "index";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="save")
    public String save(Model model) {

        return "tttr";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="importData")
    public String importData(Model model) {

        return "tttr";
    }

    @RequestMapping(value="/click", method= RequestMethod.POST, params="export")
    public String export(Model model) {

        return "tttr";
    }
}
