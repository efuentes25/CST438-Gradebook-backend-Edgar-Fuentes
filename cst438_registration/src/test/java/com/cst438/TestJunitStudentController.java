package com.cst438;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cst438.domain.StudentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestJunitStudentController {

    @Autowired
    private MockMvc mvc;

    // Lists the Students
    @Test
    public void listStudents() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.get("/student")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        StudentDTO[] students = fromJsonString(response.getContentAsString(), StudentDTO[].class);
        assertNotEquals(0, students.length);
    }

 // Adds a Student
    @Test
    public void addStudent() throws Exception {
        StudentDTO newStudent = new StudentDTO(0, "Edgar Fuentes", "ef12345@student.com", "Active", 1);

        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.post("/student")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(newStudent))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        int returnedStudentId = Integer.parseInt(response.getContentAsString());
        assertNotEquals(0, returnedStudentId);
    }
    
    // updates a students id, email, name, status, and status code
    @Test
    public void updateStudent() throws Exception {
        int studentIDUpdate = 1;  // Adjust as necessary based on your test data
        StudentDTO updatedStudent = new StudentDTO(studentIDUpdate, "Updated name", "updated12345@example.com", "Updated", 0);

        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.put("/student/" + studentIDUpdate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(updatedStudent))
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        response = mvc.perform(
                MockMvcRequestBuilders.get("/student/" + studentIDUpdate)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        StudentDTO result = fromJsonString(response.getContentAsString(), StudentDTO.class);
        assertEquals("Updated name", result.name());
    }

    // Deletes a Student
    @Test
    public void deleteStudent() throws Exception {
        int studentIDDelete = 2;  // Adjust as necessary based on your test data

        MockHttpServletResponse response = mvc.perform(
                MockMvcRequestBuilders.delete("/student/" + studentIDDelete))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());

        response = mvc.perform(
                MockMvcRequestBuilders.get("/student/" + studentIDDelete)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        assertEquals(404, response.getStatus());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static <T> T fromJsonString(String str, Class<T> valueType) {
        try {
            return new ObjectMapper().readValue(str, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
