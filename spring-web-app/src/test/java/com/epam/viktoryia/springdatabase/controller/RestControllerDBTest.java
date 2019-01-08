package com.epam.viktoryia.springdatabase.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@ContextConfiguration("/spring-context.xml")
public class RestControllerDBTest {

    private MockMvc mockMvc;
    @Autowired
    private RestController restController;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new GlobalExceptionHandler(), restController)
                .build();
    }

    @Test
    public void employeeDuplicate() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"Joe\",\"age\": \"30\"},{\"name\": \"Joe\",\"age\": \"20\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());
    }

    @Test
    public void createEmployeeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"Joe\",\"age\": \"30\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isCreated());
    }

    @Test
    public void getAllEmployeeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"Joe1\",\"age\": \"30\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isCreated());
        mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Joe1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", is(30)));

    }

    @Test
    public void updateEmployeeTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/employee/1")
                .content("\"Janett\"")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void deleteEmployeeTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}











