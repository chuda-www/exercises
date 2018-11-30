package com.epam.viktoryia.springdatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/add")
public class RestController {

    @Autowired
    CreateEmployeeService createEmployeeService;

    @Autowired
    EmployeeDAO employeeDAO;

//    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity <Employee> createEmp(@RequestBody Employee employee) {
//        HttpHeaders httpHeaders = new HttpHeaders();
//        if (employee == null) {
//            return new ResponseEntity <Employee>(HttpStatus.BAD_REQUEST);
//        }
//        employeeDAO.create(employee);
//        return new ResponseEntity <Employee>(employee, httpHeaders, HttpStatus.OK);
//    }

//    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<Employee>> getAllEmployee() {
//        List<Employee> employeeList = employeeDAO.listEmployee();
//        if (employeeList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(employeeList, HttpStatus.OK);
//    }

    @RequestMapping(value = "/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity <Employee> createEmployee(@RequestBody List <Employee> employeeList) {
        HttpHeaders httpHeaders = new HttpHeaders();
        createEmployeeService.createEmployees(employeeList);
        return new ResponseEntity <Employee>(HttpStatus.CREATED);
    }
}

