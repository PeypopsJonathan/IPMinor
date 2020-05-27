package be.ucll.task.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

public class SubTaskDTO {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;

    public SubTaskDTO(){this.id = UUID.randomUUID();}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

