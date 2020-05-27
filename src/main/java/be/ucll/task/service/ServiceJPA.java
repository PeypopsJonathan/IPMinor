package be.ucll.task.service;

import be.ucll.task.domain.SubTask;
import be.ucll.task.domain.Task;
import be.ucll.task.dto.SubTaskDTO;
import be.ucll.task.dto.TaskDTO;
import be.ucll.task.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceJPA implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public ServiceJPA(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<TaskDTO> getAll() {
        return taskRepository.findAll().stream().map(task -> {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(task.getId());
            taskDTO.setTitle(task.getTitle());
            taskDTO.setDescription(task.getDescription());
            taskDTO.setDate(task.getDate());
            taskDTO.setDueDate();

            taskDTO.setSubtasks(task.getSubtasks()
                    .stream().map(subTask -> {
                        SubTaskDTO subtaskDTO = new SubTaskDTO();
                        subtaskDTO.setId(subTask.getId());
                        subtaskDTO.setTitle(subTask.getTitle());
                        subtaskDTO.setDescription(subTask.getDescription());

                        return subtaskDTO;
                    }).collect(Collectors.toList())
            );
            return taskDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public void addTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        task.setDueDate();

        task.setSubtasks(taskDTO.getSubtasks()
                .stream().map(s -> {
                    SubTask subtask = new SubTask();
                    subtask.setId(s.getId());
                    subtask.setTitle(s.getTitle());
                    subtask.setDescription(s.getDescription());

                    return subtask;
                }).collect(Collectors.toList())
        );

        taskRepository.save(task);
    }

    @Override
    public TaskDTO getTaskDTOByID(UUID id) {
        Task task = taskRepository.findById(id).orElse(null);
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setDate(task.getDate());
        dto.setDueDate();

        dto.setSubtasks(task.getSubtasks()
                .stream().map(s -> {
                    SubTaskDTO subtaskDTO = new SubTaskDTO();
                    subtaskDTO.setId(s.getId());
                    subtaskDTO.setTitle(s.getTitle());
                    subtaskDTO.setDescription(s.getDescription());

                    return subtaskDTO;
                }).collect(Collectors.toList())
        );

        return dto;
    }

    @Override
    public Task getTaskByID(UUID id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void addTask(String naam, String description, LocalDateTime dueDate) {
        Task task = new Task(naam, description, dueDate);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.deleteById(task.getId());
        taskRepository.save(task);
    }

    @Override
    public void addSubTask(UUID taskId, SubTaskDTO subtaskDTO) {
        SubTask subtask = new SubTask();
        subtask.setTitle(subtaskDTO.getTitle());
        subtask.setDescription(subtaskDTO.getDescription());
        subtask.setId(subtaskDTO.getId());

        Task task = getTaskByID(taskId);
        task.addSubTask(subtask);

        taskRepository.save(task);
    }

    @Override
    public void editTaskFromTaskDTO(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDate(taskDTO.getDate());
        task.setDueDate();

        task.setSubtasks(taskDTO.getSubtasks()
                .stream().map(s -> {
                    SubTask subtask = new SubTask();
                    subtask.setId(s.getId());
                    subtask.setTitle(s.getTitle());
                    subtask.setDescription(s.getDescription());

                    return subtask;
                }).collect(Collectors.toList())
        );
        taskRepository.save(task);
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }
}
