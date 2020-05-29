package be.ucll.task;

import be.ucll.task.dto.SubTaskDTO;
import be.ucll.task.dto.TaskDTO;
import be.ucll.task.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
public class ServiceTest {
    @Autowired
    TaskService testTaskService;
    TaskDTO testTaskDTO = new TaskDTO();

    @BeforeEach
    public void setup() {
        testTaskDTO.setTitle("Test task");
        testTaskDTO.setDate(LocalDateTime.of(2020,12,31,23,59));
        testTaskDTO.setDueDate();
        testTaskDTO.setDescription("Test description");
        testTaskService.addTask(testTaskDTO);
    }

    @Test
    public void testGetAll(){
        List<TaskDTO> testList = testTaskService.getAll();
        assertEquals(1, testList.size());
    }

    @Test
    public void testAddTaskDTO(){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test Task Title");
        taskDTO.setDate(LocalDateTime.of(2021,1,1,11,11));
        taskDTO.setDueDate();
        taskDTO.setDescription("Test task DTO description");
        testTaskService.addTask(taskDTO);
        assertEquals(2,testTaskService.getAll().size());
    }

    @Test
    public void testGetDTOByID(){
        TaskDTO t = testTaskService.getAll().get(0);
        UUID x = t.getId();
        assertEquals(t.getTitle(),testTaskService.getTaskDTOByID(x).getTitle());
    }

    @Test
    public void testGetByID(){
        testTaskService.addTask("Test Task","something", LocalDateTime.of(2020,11,11,11,11));
        TaskDTO t = testTaskService.getAll().get(testTaskService.getAll().size()-1);
        UUID x = t.getId();
        assertEquals("Test Task", testTaskService.getTaskByID(x).getTitle());
    }

    @Test
    public void testAddSubTask(){
        testTaskService.addTask("Test Task Update","something else", LocalDateTime.of(2020,11,11,11,11));
        TaskDTO t = testTaskService.getAll().get(testTaskService.getAll().size()-1);
        UUID x = t.getId();
        TaskDTO task = testTaskService.getTaskDTOByID(x);
        SubTaskDTO subs = new SubTaskDTO();
        subs.setTitle("Sub");
        subs.setDescription("desc");
        testTaskService.addSubTask(x,subs);
        assertEquals(1, testTaskService.getTaskDTOByID(x).getSubtasks().size());
    }

    @Test
    public void testAddTask(){
        int prevSize = testTaskService.getAll().size();
        testTaskService.addTask("Test Task","Test desc", LocalDateTime.of(2020,11,11,11,11));
        assertEquals(prevSize + 1, testTaskService.getAll().size());
    }

    @Test
    public void testAddDTOWithSubTask(){
        int prevSize = testTaskService.getAll().size();
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle("Test");
        taskDTO.setDate(LocalDateTime.of(2021,11,1,11,11));
        taskDTO.setDueDate();
        taskDTO.setDescription("Test with sub");
        SubTaskDTO subTaskDTO = new SubTaskDTO();
        subTaskDTO.setTitle("SUB");
        subTaskDTO.setDescription("Something sub");
        taskDTO.addSubTask(subTaskDTO);
        testTaskService.addTask(taskDTO);
        assertEquals(prevSize + 1, testTaskService.getAll().size());
    }

}
