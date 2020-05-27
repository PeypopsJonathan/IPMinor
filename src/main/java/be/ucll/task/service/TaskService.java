package be.ucll.task.service;

import be.ucll.task.domain.Task;
import be.ucll.task.dto.SubTaskDTO;
import be.ucll.task.dto.TaskDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDTO> getAll();

    void addTask(TaskDTO task);

    TaskDTO getTaskDTOByID(UUID id);

    Task getTaskByID(UUID id);

    void addTask(String naam, String description, LocalDateTime dueDate);

    void updateTask(Task task);

    void addSubTask(UUID mainId, SubTaskDTO subTaskDTO);

    void editTaskFromTaskDTO(TaskDTO taskDTO);
}
