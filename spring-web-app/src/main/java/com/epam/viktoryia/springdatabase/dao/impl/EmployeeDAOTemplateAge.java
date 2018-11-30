package com.epam.viktoryia.springdatabase.dao.impl;

import com.epam.viktoryia.springdatabase.mapper.EmployeeMapper;
import com.epam.viktoryia.springdatabase.dao.EmployeeDAO;
import com.epam.viktoryia.springdatabase.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;

public class EmployeeDAOTemplateAge implements EmployeeDAO {

    protected JdbcTemplate jdbcTemplate;
    final private static Logger log = LogManager.getLogger(EmployeeDAOTemplateAge.class);

    public EmployeeDAOTemplateAge(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer create(Employee employee) {
        String SQL = "INSERT INTO Employee (name, age) VALUES (?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(SQL, new String[]{"ID"});
                    ps.setString(1, employee.getName());
                    ps.setInt(2, employee.getAge());
                    return ps;
                }, keyHolder);
        Number key = keyHolder.getKey();
        log.debug("Created Record Name = " + employee.toString());
        return key.intValue();
    }

    public Employee getById(Integer id) {
        String SQL = "select * from Employee where id = ?";
        Employee employees = jdbcTemplate.queryForObject(SQL, new Object[]{id}, new EmployeeMapper());
        return employees;
    }

    public List <Employee> listEmployee() {
        String SQL = "select * from Employee";
        List <Employee> employeeList = jdbcTemplate.query(SQL, new EmployeeMapper());
        return employeeList;
    }

    public void deleteById(Integer id) {
        String SQL = "delete from Employee where id = ?";
        jdbcTemplate.update(SQL, id);
        log.debug("Deleted Record with ID = " + id);
    }

    public int update(Integer id, Object object) {
        String SQL = "update Employee  set age = ? where id = ?";
        int emp = jdbcTemplate.update(SQL, object, id);
        if (emp > 0) {
            log.debug("Updated Record with ID = " + id + " Value = " + object);
        } else {
            log.debug("Record not updated");
        }
        return emp;
    }
}
