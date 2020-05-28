package be.ucll.task;


import be.ucll.task.dto.SubTaskDTO;
import be.ucll.task.domain.SubTask;
import be.ucll.task.dto.TaskDTO;
import be.ucll.task.domain.Task;
import be.ucll.task.service.TaskService;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        System.out.println(testTaskDTO.getTitle());
    }

    @Test
    public void testGetAll(){
        List<TaskDTO> testList = testTaskService.getAll();
        assertEquals(1, testList.size());
    }
}
