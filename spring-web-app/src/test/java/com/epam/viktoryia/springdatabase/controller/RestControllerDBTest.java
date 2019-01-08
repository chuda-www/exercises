package com.epam.viktoryia.springdatabase.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@RunWith(SpringRunner.class)
@ContextConfiguration("/spring-context.xml")
public class RestControllerDBTest {

    private MockMvc mockMvc;
    @Autowired
    private RestController restController;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() throws Exception {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new GlobalExceptionHandler(), restController)
                .build();
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Employee");
    }

    @Test
    public void employeeDuplicate() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"JJJ\",\"age\": \"30\"},{\"name\": \"JJJ\",\"age\": \"20\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isBadRequest());
        int rowsInTable1 = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(0, rowsInTable1);
    }

    @Test
    public void createEmployeeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("[{\"name\": \"AAA\",\"age\": \"30\"}]"))
                .andExpect(MockMvcResultMatchers.status()
                        .isCreated());
        int rowsInTable1 = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(1, rowsInTable1);
    }

    @Test
    public void getAllEmployeeTest() throws Exception {
        String SQL = "INSERT INTO Employee (name, age) VALUES ('YYY', 30)";
        jdbcTemplate.update(SQL);

        mockMvc.perform(MockMvcRequestBuilders.get("/employee"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("YYY"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].age").value(30));
        int rowsInTable1 = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(1, rowsInTable1);
    }

    @Test
    public void updateEmployeeTest() throws Exception {
        String SQL = "INSERT INTO Employee (id, name, age) VALUES (1, 'VVV', 40)";
        jdbcTemplate.update(SQL);
        mockMvc.perform(MockMvcRequestBuilders.put("/employee/1")
                .content("\"XXX\"")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
        int rowsInTable1 = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "Employee", "name = 'XXX'");
        Assert.assertEquals(1, rowsInTable1);
    }

    @Test
    public void deleteEmployeeTest() throws Exception {
        String SQL = "INSERT INTO Employee (id, name, age) VALUES (1, 'ABC', 40)";
        jdbcTemplate.update(SQL);
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        int rowsInTable1 = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(0, rowsInTable1);
    }
}











