package com.epam.viktoryia.springdatabase;

import com.epam.viktoryia.springdatabase.model.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientRestTemplate {

    public static void main(String args[]) {

        RestTemplate restTemplate = new RestTemplate();
        System.out.println("Для отправки POST-запроса нажмите 1: ");
        System.out.println("Для отправки GET-запроса нажмите 2: ");
        System.out.println("Для отправки PUT-запроса нажмите 3: ");
        System.out.println("Для отправки DELETE-запроса нажмите 4: ");
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int i = scanner.nextInt();
            if (i == 1) {
                try {
                    doPost(restTemplate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                scanner.nextInt();
            }
            if (i == 2) {
                doGet(restTemplate);
                scanner.nextInt();
            }
            if (i == 3) {
                try {
                    doPut(restTemplate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                scanner.nextInt();
            }
            if (i == 4) {
                doDelete(restTemplate);
                scanner.nextInt();
            }
        }
    }

    public static void doPost(RestTemplate restTemplate) throws Exception {
        final String uri = "http://localhost:8080/employee/";
        List <Employee> employeeList = new ArrayList <>();
        Employee employee = new Employee();
        employee.setName("ggg");
        employee.setAge(30);
        employeeList.add(employee);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity httpEntity = new HttpEntity(employeeList, headers);
        List <Employee> response = restTemplate.postForObject(
                uri,
                httpEntity,
                List.class);
        System.out.println("-- response --");
        System.out.println(employeeList);
    }

    public static void doGet(RestTemplate restTemplate) {
        final String uri = "http://localhost:8080/employee/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        List <Employee> response = restTemplate.getForObject(
                uri, List.class);
        System.out.println("-- response --");
        System.out.println("Get response: " + response);
    }

    public static void doPut(RestTemplate restTemplate) throws Exception {
        final String uri = "http://localhost:8080/employee/1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity <String> httpEntity = new HttpEntity("lll", headers);

        restTemplate.put(uri, httpEntity);
        System.out.println("-- response --");
        System.out.println("PUT-запрос выполнен ");
    }

    public static void doDelete(RestTemplate restTemplate) {
        final String uri = "http://localhost:8080/employee/1";

        restTemplate.delete(uri);
        System.out.println("-- response --");
        System.out.println("DELETE-запрос выполнен ");
    }
}