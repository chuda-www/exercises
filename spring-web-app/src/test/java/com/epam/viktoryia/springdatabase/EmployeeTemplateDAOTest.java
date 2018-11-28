package com.epam.viktoryia.springdatabase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")

public class EmployeeTemplateDAOTest {

    @Autowired
    private EmployeeDAOTemplateAge employeeDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() throws Exception {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "Employee");
    }

    @Test(expected = DuplicateKeyException.class)
    public void createTestExeption() {

        Employee employee3 = createEmployeeObject("Anna", 11);
        Employee employee4 = createEmployeeObject("Anna", 22);
        employeeDAO.create(employee3);
        employeeDAO.create(employee4);
    }

    @Test
    public void createTest() {

        //create
        Employee employee1 = createEmployeeObject("Zara", 11);
        Integer createIdEmployee = employeeDAO.create(employee1);
        System.out.println("Create record: " + employeeDAO.listEmployee());
        int rowsInTable = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertTrue(rowsInTable == 1);

        // getById
        Employee resultGetById = employeeDAO.getById(createIdEmployee);
        Assert.assertNotNull(resultGetById);
        System.out.println(resultGetById);
        Assert.assertEquals("Zara", resultGetById.getName());

        //update
        int emp = employeeDAO.update(createIdEmployee, "Jane");
        int n = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "Employee", "name = 'Jane' ");
        Assert.assertTrue(n == 1);
        Assert.assertTrue(emp == 1);

        //listEmployee
        List <Employee> list = employeeDAO.listEmployee();
        System.out.println("All loaded list: " + list);
        Assert.assertTrue(list.size() == 1);

        //deleteById
        employeeDAO.deleteById(createIdEmployee);
        System.out.println("List: " + employeeDAO.listEmployee());
        rowsInTable = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Employee");
        Assert.assertTrue(rowsInTable == 0);
    }

    private static Employee createEmployeeObject(String name, Integer age) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }
}

