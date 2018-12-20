package com.epam.viktoryia.springdatabase.service;

import com.epam.viktoryia.springdatabase.model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration("/spring-context.xml")
public class EmployeesServiceDBTest {

    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Employee");
    }

    @Test
    public void getTest() {
        int rowsInTable = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(0, rowsInTable);
    }

    @Test
    public void getEmployees() {
        String SQL = "INSERT INTO Employee (name, age) VALUES ('Mamba', 30)";
        jdbcTemplate.update(SQL);
        List <Employee> list = employeesService.getEmployees();
        System.out.println(list.size());
        Assert.assertEquals(1, list.size());
        System.out.println(list);
    }

    @Test
    public void createEmployees() {
//        Init
        List <Employee> employeeList = employeesService.getEmployees();
        employeeList.add(createEmployeeObject("Yulia", 11));
        employeeList.add(createEmployeeObject("Grad", 52));

//        Execute
        employeesService.createEmployees(employeeList);

//        Validate
        int rowsInTable = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(2, rowsInTable);
        Assert.assertNotNull(employeeList);
        System.out.print("list  " + employeeList);
    }

    @Test
    public void createTestExeption() {
        List <Employee> employeeList = new ArrayList <>();
        employeeList.add(createEmployeeObject("AAA", 11));
        employeeList.add(createEmployeeObject("AAA", 52));
        try {
            employeesService.createEmployees(employeeList);
        } catch (Exception e) {
            System.out.println("DuplicateKeyException");
        }
        int rowsInTable1 = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(0, rowsInTable1);
    }

    @Test
    public void updateEmployeesTest() {
        String SQL = "INSERT INTO Employee (id, name, age) VALUES (1, 'Jasmin', 40)";
        jdbcTemplate.update(SQL);
        System.out.println(employeesService.getEmployees());
        employeesService.updateEmployees(1, "Masha");
        System.out.println(employeesService.getEmployees());
        int rowsInTable1 = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "Employee", "name = 'Masha'");
        Assert.assertEquals(1, rowsInTable1);
    }

    @Test
    public void deleteEmployeeByIdTest() {
        String SQL = "INSERT INTO Employee (id, name, age) VALUES (1, 'Jasmin', 40)";
        jdbcTemplate.update(SQL);
        System.out.println(employeesService.getEmployees());
        employeesService.deleteEmployeeById(1);
        System.out.println(employeesService.getEmployees());
        int rowsInTable1 = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertEquals(0, rowsInTable1);
    }

    private static Employee createEmployeeObject(String name, Integer age) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }
}