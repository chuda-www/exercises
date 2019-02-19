package com.epam.viktoryia.springdatabase.service;

import com.epam.viktoryia.springdatabase.dao.EmployeeDAO;
import com.epam.viktoryia.springdatabase.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class EmployeesService {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Transactional
    public void createEmployees(List <Employee> employeeList) {
        for (Employee employee : employeeList) {
            employeeDAO.create(employee);
        }
    }

    public Employee getEmployeeById(Integer id) {
        return employeeDAO.getById(id);
    }

    public List <Employee> getEmployees() {
        return employeeDAO.listEmployee();
    }

    public void updateEmployees(Integer id, Object object) {
        employeeDAO.update(id, object);
    }

    public void deleteEmployeeById(Integer id) {
        employeeDAO.deleteById(id);
    }

}
