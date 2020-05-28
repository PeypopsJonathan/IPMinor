package be.ucll.task;

import be.ucll.task.dto.SubTaskDTO;
import be.ucll.task.dto.TaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TaskTestDTO {

    TaskDTO testTaskDTO = new TaskDTO();

    @Test
    public void setterTestTaskDTO(){
        testTaskDTO.setTitle("TestTaskDTO");
        testTaskDTO.setDescription("Test task description");
        testTaskDTO.setDate(LocalDateTime.of(2121,12,21,21,21));
        testTaskDTO.setDueDate();
        assertEquals("TestTaskDTO", testTaskDTO.getTitle());
        assertEquals("Test task description", testTaskDTO.getDescription());
        assertEquals(LocalDateTime.of(2121,12,21,21,21), testTaskDTO.getDate());
    }

    @Test
    public void testAddSubTask(){
        SubTaskDTO testSubTaskDTO = new SubTaskDTO();
        testTaskDTO.addSubTask(testSubTaskDTO);
        assertNotNull(testSubTaskDTO);
        assertEquals(testSubTaskDTO, testTaskDTO.getSubTask(testSubTaskDTO.getId()));
        assertEquals(1, testTaskDTO.getSubtasks().size());
    }
}
