package com.example.simplewebapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskController
 *
 * @Author Tretyakov Alexandr
 */

@Controller
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @GetMapping("/task/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        return "create";

    }

    @PostMapping("/task/create")
    public String createTask(@ModelAttribute Task task) {
        task.setId(System.currentTimeMillis());
        tasks.add(task);
        return "redirect:/";
    }

    @GetMapping("/task/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = getById(id);
        if (task != null) {
            model.addAttribute("task", task);
            return "edit";
        }
        return "redirect:/";
    }

    @PostMapping("/task/edit")
    public String editTask(@ModelAttribute Task task) {
        Task taskOld = getById(task.getId());
        taskOld.setTitle(task.getTitle());
        taskOld.setDescription(task.getDescription());
        taskOld.setPriority(task.getPriority());
        return "redirect:/";

    }

    @GetMapping("/task/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        Task task = getById(id);
        if (task != null) tasks.remove(task);
        return "redirect:/";
    }

    private Task getById(Long id) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) return task;
        }
        return null;
    }

    //альтернатива
    private Task findById(Long id) {
        return tasks.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


}
