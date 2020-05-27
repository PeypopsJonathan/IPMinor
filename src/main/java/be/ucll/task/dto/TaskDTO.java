package be.ucll.task.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TaskDTO {
    private UUID id;
    private String title;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private String description;
    private String dueDate;
    private List<SubTaskDTO> subtasks;

    public TaskDTO() {

        subtasks = new ArrayList<>();
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate() {
        this.dueDate = date.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM,FormatStyle.SHORT));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null || title.length() == 0) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title is required");
        }
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime dueDate) {
        this.date = dueDate;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubTaskDTO> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTaskDTO> subtasks) {
        this.subtasks = subtasks;
    }

    public void addSubTask(SubTaskDTO subTask){
        this.subtasks.add(subTask);
    }
}
