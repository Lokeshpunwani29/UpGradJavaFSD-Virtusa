package com.java.spring;

import com.java.spring.model.Employ;
import com.java.spring.service.EmployService;
import com.java.spring.controller.EmployController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployControllerTest {

    @InjectMocks
    private EmployController employController;

    @Mock
    private EmployService employService;


    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(employController).build();
    }

    // -------------------------------
    // Test showEmploy()
    // -------------------------------
    @Test
    void testShowEmploy() throws Exception {
        Employ e1 = new Employ(1, "Prasanna", "MALE", "Java", "Programmer", 50000);
        Employ e2 = new Employ(2, "Kumar", "MALE", "Java", "Manager", 99323);

        List<Employ> result  = Arrays.asList(e1, e2);
        when(employService.showEmploy()).thenReturn(result);

        mockMvc.perform(get("/employ/showEmploy"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].empno").value(1))
                .andExpect(jsonPath("$[0].name").value("Prasanna"))
         .andExpect(jsonPath("$[0].gender").value("MALE"))
                .andExpect(jsonPath("$[0].dept").value("Java"))
                .andExpect(jsonPath("$[0].desig").value("Programmer"))
                .andExpect(jsonPath("$[0].basic").value(50000));
    }

    // -------------------------------
    // Test addEmploy()
    // -------------------------------
    @Test
    void testAddEmploy() throws Exception {

        when(employService.addEmploy(Mockito.any(Employ.class)))
                .thenReturn("Employ Record Inserted...");

        String jsonBody = """
                {
                  "empno": 100,
                  "ename": "Lokesh",
                  "gender": "MALE",
                  "dept":"Java",
                  "desig":"Manager",
                  "salary": 99323
                }
                """;

        mockMvc.perform(post("/employ/addEmploy")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Employ Record Inserted..."));
    }

    // -------------------------------
    // Test searchEmploy()
    // -------------------------------
    @Test
    void testSearchEmploy() throws Exception {
        Employ e1 = new Employ(10, "Prasanna", "MALE", "Java", "Programmer", 50000);

        when(employService.searchEmploy(10)).thenReturn(e1);

        mockMvc.perform(get("/employ/search/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Prasanna"))
                .andExpect(jsonPath("$.basic").value(50000));
    }
}