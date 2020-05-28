package be.ucll.task;

import be.ucll.task.domain.SubTask;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SubTaskTest {
    SubTask testSubTask = new SubTask("Test Subtask", "Test subtask description");

    @Test
    public void createSubtask(){
        SubTask subTask = new SubTask("Test Subtask 2", "Test subtask description 2");
        UUID uuid = subTask.getId();
        assertNotNull(subTask);
        assertEquals("Test Subtask 2", subTask.getTitle());
        assertEquals("Test subtask description 2", subTask.getDescription());
        assertEquals(uuid, subTask.getId());
    }

    @Test
    public void setterSubTaskTest(){
        testSubTask.setTitle("Setter test title");
        testSubTask.setDescription("Setter test description");
        assertEquals("Setter test title", testSubTask.getTitle());
        assertEquals("Setter test description",testSubTask.getDescription());
    }

    @Test
    public void getterSubTaskTest(){
        assertEquals("Test Subtask", testSubTask.getTitle());
        assertEquals("Test subtask description", testSubTask.getDescription());
    }


}
