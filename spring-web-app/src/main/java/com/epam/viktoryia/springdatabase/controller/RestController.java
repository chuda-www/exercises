package com.epam.viktoryia.springdatabase.controller;

import com.epam.viktoryia.springdatabase.model.Employee;
import com.epam.viktoryia.springdatabase.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class RestController {

    @Autowired
    private EmployeesService employeesService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createEmployee(@RequestBody List <Employee> employeeList) {
        employeesService.createEmployees(employeeList);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public List <Employee> getAllEmployee() {
        return employeesService.getEmployees();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateEmployee(@PathVariable("id") Integer id, @RequestBody Object object) {
        employeesService.updateEmployees(id, object);
        return new ResponseEntity(id, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteEmployee(@PathVariable Integer id) {
        employeesService.deleteEmployeeById(id);
    }
}

