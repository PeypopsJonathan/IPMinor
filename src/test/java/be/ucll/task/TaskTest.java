package be.ucll.task;

import be.ucll.task.domain.SubTask;
import be.ucll.task.domain.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TaskTest {
    Task testTask = new Task("TestTask_1", "Description test", LocalDateTime.of(2020,05,31,23,59));


    @Test
    public void testTask(){
        Task task = new Task("Task", "Description", LocalDateTime.of(2020,5,29,19,00));

        UUID uuid = task.getId();

        assertNotNull(task);
        assertEquals("Description", task.getDescription());
        assertEquals("Task", task.getTitle());
        assertEquals(uuid, task.getId());
        assertEquals(LocalDateTime.of(2020, 5, 29, 19,00), task.getDate());
    }
   @Test
    public void testTaskSetSubTask(){
        Task task = new Task("Task", "Description", LocalDateTime.of(2020,5,29,19,00));
        SubTask subTask = new SubTask("Sub Title", "Sub description");

        task.addSubTask(subTask);
        assertNotNull(task);
        assertNotNull(subTask);
        assertEquals(subTask, task.getSubTask(subTask));
        assertEquals(1, task.getSubtasks().size());
    }

    @Test
    public void taskSetterTest(){
        testTask.setTitle("Setter title");
        testTask.setDescription("Setter description");
        testTask.setDate(LocalDateTime.of(2121, 12,21,21,21));
        testTask.setDueDate();
        assertEquals("Setter title", testTask.getTitle());
        assertEquals("Setter description", testTask.getDescription());
        assertEquals(LocalDateTime.of(2121, 12,21,21,21), testTask.getDate());
    }

}
