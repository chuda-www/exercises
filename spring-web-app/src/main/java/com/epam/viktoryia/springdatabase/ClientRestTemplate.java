package com.epam.viktoryia.springdatabase;

import com.epam.viktoryia.springdatabase.model.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClientRestTemplate {

    private static RestTemplate restTemplate = new RestTemplate();
    private static List <Employee> employeeList = new ArrayList <>();
    private static Employee employee = new Employee();

    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Для отправки POST-запроса нажмите 1: " +
                "\nДля отправки GET-запроса нажмите 2: " +
                "\nДля отправки PUT-запроса нажмите 3: " +
                "\nДля отправки DELETE-запроса нажмите 4:");
        try {
            while (scanner.hasNext()) {
                int i = scanner.nextInt();
                if (i == 1) {
                    doPost();
                }
                if (i == 2) {
                    doGet();
                }
                if (i == 3) {
                    doPut();
                }
                if (i == 4) {
                    doDelete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.next();
        }
    }

    private static void doPost() throws RestClientException {
        final String uri = "http://localhost:8080/employee/";

        employee.setName("ggg");
        employee.setAge(30);
        employeeList.add(employee);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity httpEntity = new HttpEntity(employeeList, headers);
        List <Employee> response = restTemplate.postForObject(uri, httpEntity, List.class);

        System.out.println("-- response --");
        System.out.println(employeeList);
    }

    private static void doGet() {
        final String uri = "http://localhost:8080/employee/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        List <Employee> response = restTemplate.getForObject(uri, List.class);

        System.out.println("-- response --");
        System.out.println("Get response: " + response);
        System.out.println(employeeList);
    }

    private static void doPut() throws RestClientException {
        final String uri = "http://localhost:8080/employee/{id}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity <String> httpEntity = new HttpEntity("\"uuu\"", headers);
        restTemplate.put(uri, httpEntity, 1);

        System.out.println("PUT-запрос выполнен ");
    }

    private static void doDelete() {
        final String uri = "http://localhost:8080/employee/{id}";

        restTemplate.delete(uri, 1);

        System.out.println("DELETE-запрос выполнен ");
    }
}