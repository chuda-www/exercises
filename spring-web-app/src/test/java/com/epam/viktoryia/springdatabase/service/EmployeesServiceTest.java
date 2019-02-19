package com.epam.viktoryia.springdatabase.service;

import com.epam.viktoryia.springdatabase.dao.EmployeeDAO;
import com.epam.viktoryia.springdatabase.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesServiceTest {
    @InjectMocks
    private EmployeesService employeesService;
    @Mock
    private EmployeeDAO employeeDAO;

    @Test
    public void createEmployees() {
        Employee employee1 = createEmployeeObject(1,"Yulia", 11);
        Employee employee2 = createEmployeeObject(2,"Grad", 52);
        List <Employee> employeeList = new ArrayList <>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeesService.createEmployees(employeeList);
        verify(employeeDAO, times(2)).create(anyObject());
        System.out.print("list  " + employeeList.toString());
    }

    @Test
    public void getEmployees() {
        employeesService.getEmployees();
        verify(employeeDAO, times(1)).listEmployee();
    }

    @Test
    public void getEmployeesById() {
        employeesService.getEmployeeById(1);
        verify(employeeDAO, times(1)).getById(anyInt());
    }

    private static Employee createEmployeeObject(Integer id, String name, Integer age) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }
}