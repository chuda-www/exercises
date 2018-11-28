package com.epam.viktoryia.springdatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class CreateEmployeeServiceDBTest {

    @Autowired
    private CreateEmployeeService createEmployeeService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Employee");
    }

    @Test
    public void createEmployees() {
//        Init
        List <Employee> employeeList = new ArrayList <>();
        employeeList.add(createEmployeeObject("Yulia", 11));
        employeeList.add(createEmployeeObject("Grad", 52));

//        Execute
        createEmployeeService.createEmployees(employeeList);

//        Validate
        int rowsInTable = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertTrue(rowsInTable == 2);
        Assert.assertNotNull(employeeList);
        System.out.print("list  " + employeeList);
    }

    @Test
    public void createTestExeption() {
        List <Employee> employeeList = new ArrayList <>();
        employeeList.add(createEmployeeObject("AAA", 11));
        employeeList.add(createEmployeeObject("AAA", 52));
        try {
            createEmployeeService.createEmployees(employeeList);
        } catch (Exception e) {
            System.out.println("DuplicateKeyException");
        }
        int rowsInTable1 = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertTrue(rowsInTable1 == 0);
    }

    private static Employee createEmployeeObject(String name, Integer age) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }
}