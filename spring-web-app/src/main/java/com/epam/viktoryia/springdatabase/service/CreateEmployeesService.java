package com.epam.viktoryia.springdatabase.service;

import com.epam.viktoryia.springdatabase.dao.EmployeeDAO;
import com.epam.viktoryia.springdatabase.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CreateEmployeesService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public void createEmployees(List <Employee> employeeList) {
        for (Employee employee : employeeList) {
            employeeDAO.create(employee);
        }
    }
    public List <Employee> getEmployees() {
        return employeeDAO.listEmployee();
    }
}
