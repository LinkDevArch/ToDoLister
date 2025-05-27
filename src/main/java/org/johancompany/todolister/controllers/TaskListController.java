package org.johancompany.todolister.controllers;

import jakarta.validation.Valid;
import org.johancompany.todolister.dtos.TaskDTO;
import org.johancompany.todolister.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tasks")
public class TaskListController {

    private final TaskService taskService;

    public TaskListController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    public String showTaskList(Model model) {
        try{
            model.addAttribute("tasks", taskService.getAllTasks());
        }catch (Exception e){
            model.addAttribute("message", e.getMessage());
        }
        return "taskList";
    }

    @PostMapping("/save")
    public String saveTask(@Valid TaskDTO taskDTO) {
        taskService.createTask(taskDTO);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTaskStatus(@PathVariable Long id) {

        try{
            taskService.toggleTaskStatus(id);
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage() + "");
        }
        return "redirect:/tasks";
    }

}
