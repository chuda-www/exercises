package com.epam.viktoryia.springdatabase.controller;

import com.epam.viktoryia.springdatabase.model.Employee;
import com.epam.viktoryia.springdatabase.service.CreateEmployeesService;
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
    private CreateEmployeesService createEmployeesService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity createEmployee(@RequestBody List <Employee> employeeList) {
        createEmployeesService.createEmployees(employeeList);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List <Employee> getAllEmployee() {
        return createEmployeesService.getEmployees();
    }
}

