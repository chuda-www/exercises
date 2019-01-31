package com.epam.viktoryia.springdatabase;

import com.epam.viktoryia.springdatabase.model.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClientRestTemplateTest extends Mockito {

    private static final Integer ID = 1;
    private static final String URL = "http://localhost:8080/employee/";

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ClientRestTemplate clientRestTemplate;

    private Employee createEmployeeObject(Integer id, String name, Integer age) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }

    private List <Employee> employeeList = new ArrayList <>();

    @Test
    public void testDoPost() throws Exception {
        employeeList.add(createEmployeeObject(ID, "aaa", 20));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity httpEntity = new HttpEntity(employeeList, headers);

        clientRestTemplate.doPost();
        verify(restTemplate).postForObject(URL, httpEntity, List.class);
    }

    @Test
    public void testDoGet() throws Exception {

        clientRestTemplate.doGet();
        verify(restTemplate, times(1)).getForObject(URL, List.class);
    }

    @Test
    public void testDoPut() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity <String> httpEntity = new HttpEntity("\"uuu\"", headers);

        clientRestTemplate.doPut();
        verify(restTemplate, times(1)).put(URL + ID, httpEntity);
    }

    @Test
    public void testDoDelete() throws Exception {

        clientRestTemplate.doDelete();
        verify(restTemplate, times(1)).delete(URL + ID);
    }
}
