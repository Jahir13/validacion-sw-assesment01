package com.viraj.sample.controller;

import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setup() {
        employeeRepository.deleteAll();
    }

    @Test
    void testSaveEmployee() throws Exception {
        String employeeJson = "{\"employeeName\":\"John Doe\",\"employeeDescription\":\"Developer\"}";

        mockMvc.perform(post("/employee/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("John Doe"))
                .andExpect(jsonPath("$.employeeDescription").value("Developer"));
    }

    @Test
    void testGetEmployee() throws Exception {
        Employee emp = new Employee("Bob", "Analyst");
        Employee saved = employeeRepository.save(emp);

        mockMvc.perform(get("/employee/getone/{employeeId}", saved.getEmployeeId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("Bob"))
                .andExpect(jsonPath("$.employeeDescription").value("Analyst"));
    }
}
