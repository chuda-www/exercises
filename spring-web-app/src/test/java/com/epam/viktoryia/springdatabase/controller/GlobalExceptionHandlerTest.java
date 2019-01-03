package com.epam.viktoryia.springdatabase.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest extends Mockito {

    private MockMvc mockMvc;
    @Mock
    private RestController restController;

    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new GlobalExceptionHandler(), restController)
                .build();
    }

    @Test
    public void employeeDuplicateTest() throws Exception {

        when(restController.createEmployee(anyList())).thenThrow(new DuplicateKeyException(anyString()));

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"Joe\",\"age\": \"31\"},{\"name\": \"Joe\",\"age\": \"30\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .string("*****DuplicateKeyException : "));
    }
}