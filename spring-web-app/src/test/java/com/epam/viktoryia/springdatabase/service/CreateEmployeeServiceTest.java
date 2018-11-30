package com.epam.viktoryia.springdatabase.service;

import com.epam.viktoryia.springdatabase.dao.EmployeeDAO;
import com.epam.viktoryia.springdatabase.model.Employee;
import com.epam.viktoryia.springdatabase.service.CreateEmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CreateEmployeeServiceTest {
    @InjectMocks
    private CreateEmployeeService createEmployeeService;
    @Mock
    private EmployeeDAO employeeDAO;

    @Test
    public void createEmployees() {
        Employee employee1 = createEmployeeObject("Yulia", 11);
        Employee employee2 = createEmployeeObject("Grad", 52);
        List <Employee> employeeList = new ArrayList <>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        createEmployeeService.createEmployees(employeeList);
        verify(employeeDAO, times(2)).create(anyObject());
        System.out.print("list  " + employeeList.toString());
    }

    private static Employee createEmployeeObject(String name, Integer age) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }
}