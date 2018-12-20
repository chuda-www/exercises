package com.epam.viktoryia.springdatabase.controller;

import com.epam.viktoryia.springdatabase.model.Employee;
import com.epam.viktoryia.springdatabase.service.EmployeesService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(MockitoJUnitRunner.class)
public class RestControllerTest extends Mockito {

    private MockMvc mockMvc;
    @Mock
    private EmployeesService employeesService;
    @InjectMocks
    private RestController restController;

    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(restController)
                .build();
    }

    @Test
    public void createEmployeeTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"Joe\",\"age\": \"30\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isCreated());

        verify(employeesService, times(1)).createEmployees(anyList());
    }

    @Test
    public void getAllEmployeeTest() throws Exception {
        List <Employee> employeeList = new ArrayList <>();
        Employee employee1 = new Employee();
        employee1.setAge(30);
        employee1.setName("Joe");
        employee1.setId(1);
        employeeList.add(employee1);
        when(employeesService.getEmployees()).thenReturn(employeeList);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Joe")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age", is(30)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)));

        verify(employeesService, times(1)).getEmployees();
    }

    @Test
    public void updateEmployeeTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.put("/employee/1")
                .content("\"Janett\"")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(employeesService, times(1)).updateEmployees(1,"Janett");
    }

    @Test
    public void deleteEmployeeTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(employeesService, times(1)).deleteEmployeeById(anyInt());
    }
}