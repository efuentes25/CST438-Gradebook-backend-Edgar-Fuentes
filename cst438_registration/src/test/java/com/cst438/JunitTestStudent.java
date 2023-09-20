package com.cst438;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;
import java.util.Optional;

import com.cst438.controller.StudentController;
import com.cst438.domain.Student;
import com.cst438.domain.StudentDTO;
import com.cst438.domain.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

// This is not the correct test assignment. 

@SpringBootTest
@AutoConfigureMockMvc
/*public class JunitTestStudent {

	@Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStudent() throws Exception {
        // Mock a Student object
        Student student = new Student();
        student.setStudent_id(1);
        student.setName("John Doe");
        student.setEmail("johndoe@example.com");
        student.setStatus("Active");
        student.setStatusCode(1);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(
                "{\"student_id\":1,\"name\":\"John Doe\",\"email\":\"johndoe@example.com\",\"status\":\"Active\",\"status_code\":\"ACTIVE\"}"
            ));
    }

    @Test
    public void testCreateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO(0, null, null, null, 0);
        studentDTO.setName("Jane Smith");
        studentDTO.setEmail("janesmith@example.com");
        studentDTO.setStatus("Active");
        studentDTO.setStatus_code(1);

        when(studentRepository.save(any(Student.class))).thenReturn(new Student());

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(studentDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testUpdateStudent() throws Exception {
        StudentDTO studentDTO = new StudentDTO(0, null, null, null, 0);
        studentDTO.setName("Updated Name");
        studentDTO.setEmail("updated@example.com");
        studentDTO.setStatus("Inactive");
        studentDTO.setStatus_code(0);

        Student student = new Student();
        student.setStudent_id(1);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders.put("/student/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(studentDTO)))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(studentRepository, times(1)).save(any(Student.class));
    }

    @Test
    public void testDeleteStudent() throws Exception {
        Student student = new Student();
        student.setStudent_id(1);

        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders.delete("/student/1"))
            .andExpect(MockMvcResultMatchers.status().isOk());

        verify(studentRepository, times(1)).delete(student);
    }*/
}
