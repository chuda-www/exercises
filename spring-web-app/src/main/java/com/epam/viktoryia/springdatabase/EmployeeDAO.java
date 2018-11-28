package com.epam.viktoryia.springdatabase;

import java.util.List;

interface EmployeeDAO {

    Integer create(Employee employee);

    Employee getById(Integer id);

    int update(Integer id, Object object);

    void deleteById(Integer id);

    List <Employee> listEmployee();

}

