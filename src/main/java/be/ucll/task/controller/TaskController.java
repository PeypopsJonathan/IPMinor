package be.ucll.task.controller;

import be.ucll.task.domain.Task;
import be.ucll.task.dto.SubTaskDTO;
import be.ucll.task.dto.TaskDTO;
import be.ucll.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public String home() {
        return "fragments/header";
    }

    @GetMapping("/tasks")
    public String tasks(Model model) {
        model.addAttribute("Tasks", taskService.getAll());
        return "task";
    }

    @GetMapping("/tasks/{id}")
    public String taskDetail(Model model, @PathVariable UUID id) {
        model.addAttribute("TaskDetail", taskService.getTaskByID(id));
        model.addAttribute("id", id);
        return "overview";
    }

    @GetMapping("/tasks/new")
    public String newTask(Model model) {
        model.addAttribute("Task",new TaskDTO());
        return "addTask";
    }

    @PostMapping("/tasks/new")
    public String addTask(@ModelAttribute TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/edit/{id}")
    public String edit(Model model, @PathVariable UUID id) {
        model.addAttribute("Task", taskService.getTaskByID(id));
        return "editTask";
    }

    @PostMapping("/tasks/edit/{id}")
    public String editTask(@ModelAttribute Task task, @RequestParam UUID id, TaskDTO taskDTO) {
        task.setId(id);
        taskDTO.setId(id);
        taskService.editTaskFromTaskDTO(taskDTO);
        return "redirect:/tasks/" + task.getId();
    }

    @GetMapping("/tasks/{id}/sub/create")
    public String sub(Model model, @PathVariable UUID id) {
        model.addAttribute("SubTask", new SubTaskDTO());
        return "addSubTask";
    }

    @PostMapping("/tasks/{id}/sub/create")
    public String addSubTask(@PathVariable UUID id, @ModelAttribute SubTaskDTO subtaskDTO){
        taskService.addSubTask(id, subtaskDTO);
        return "redirect:/tasks/"+ id;
    }
}
