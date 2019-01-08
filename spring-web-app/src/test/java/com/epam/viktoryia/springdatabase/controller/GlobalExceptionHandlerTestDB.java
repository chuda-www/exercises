package com.epam.viktoryia.springdatabase.controller;

import com.epam.viktoryia.springdatabase.service.EmployeesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
@ContextConfiguration("/spring-context.xml")
public class GlobalExceptionHandlerTestDB {

    private MockMvc mockMvc;
    @Mock
    private EmployeesService employeesService;
    @Autowired
    private RestController restController;

    @Before
    public void init() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new GlobalExceptionHandler(),restController)
                .build();
    }

    @Test
    public void employeeDuplicate() throws Exception {

        doThrow(new DuplicateKeyException("abc")).when(employeesService).createEmployees(anyList());

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"Joe\",\"age\": \"30\"},{\"name\": \"Joe\",\"age\": \"20\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());
    }
}











