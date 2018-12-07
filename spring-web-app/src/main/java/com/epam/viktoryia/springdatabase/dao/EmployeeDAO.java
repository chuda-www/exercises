package com.epam.viktoryia.springdatabase.dao;

import com.epam.viktoryia.springdatabase.model.Employee;

import java.util.List;

public interface EmployeeDAO {

    Integer create(Employee employee);

    Employee getById(Integer id);

    int update(Integer id, Object object);

    void deleteById(Integer id);

    List <Employee> listEmployee();

}

