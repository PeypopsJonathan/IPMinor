package be.ucll.task.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String title;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime date;
    private String description;
    private String dueDate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubTask> subtasks;

    public Task() {
        this.id = UUID.randomUUID();
        subtasks = new ArrayList<>();
    }

    public Task(String title, String description, LocalDateTime dueDate) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.date = dueDate;
        setDueDate();
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

    public List<SubTask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(List<SubTask> subtasks) {
        this.subtasks = subtasks;
    }

    public void addSubTask(SubTask subTask){
        this.subtasks.add(subTask);
    }

    public SubTask getSubTask(SubTask subtask){
        for(SubTask s : this.subtasks){
            if(s.getId().equals(subtask.getId())){
                return s;
            }
        }
        return null;
    }
}
