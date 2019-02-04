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

    private static final Integer ID = 1;
    private static final String URL = "http://localhost:8080/employee/";

    private RestTemplate restTemplate = new RestTemplate();

    private Employee createEmployeeObject(Integer id, String name, Integer age) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setName(name);
        employee.setAge(age);
        return employee;
    }

    private List <Employee> employeeList = new ArrayList <>();

    public static void main(String args[]) {
        ClientRestTemplate client = new ClientRestTemplate();
        Scanner scanner = new Scanner(System.in);

        menu();

        try {
            while (scanner.hasNext()) {
                switch (scanner.nextInt()) {
                    case 1:
                        client.doPost();
                        break;
                    case 2:
                        client.doGet();
                        break;
                    case 3:
                        client.doPut();
                        break;
                    case 4:
                        client.doDelete();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid.");
                        break;
                }
                menu();
            }
        } catch (Exception e) {
            System.out.println("Excseption :" + e.getMessage());
            scanner.nextInt();
        }
    }

    private static void menu() {
        System.out.println("Для отправки POST-запроса нажмите 1: " +
                "\nДля отправки GET-запроса нажмите 2: " +
                "\nДля отправки PUT-запроса нажмите 3: " +
                "\nДля отправки DELETE-запроса нажмите 4: " +
                "\nДля выхода нажмите 5: ");
    }

    void doPost() throws Exception {
        employeeList.add(createEmployeeObject(ID, "aaa", 20));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity httpEntity = new HttpEntity(employeeList, headers);

        restTemplate.postForObject(URL, httpEntity, List.class);
        System.out.println("POST выполнен");
    }

    void doGet() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        List <Employee> response = restTemplate.getForObject(URL, List.class);

        System.out.println("GET выполнен: " + response);
    }

    void doPut() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity <String> httpEntity = new HttpEntity("\"uuu\"", headers);
        restTemplate.put(URL + ID, httpEntity);

        System.out.println("PUT выполнен ");
    }

    void doDelete() throws Exception {

        restTemplate.delete(URL + ID);

        System.out.println("DELETE выполнен ");
    }
}