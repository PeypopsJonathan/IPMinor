package be.ucll.task;


import be.ucll.task.dto.SubTaskDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SubTaskDTOTest {

    SubTaskDTO testSubTaskDTO = new SubTaskDTO();


    @Test
    public void setterTest(){
        testSubTaskDTO.setTitle("Test subtaskdto title");
        testSubTaskDTO.setDescription("Test subtaskdto description");
        assertEquals("Test subtaskdto title", testSubTaskDTO.getTitle());
        assertEquals("Test subtaskdto description", testSubTaskDTO.getDescription());
    }
}
