package com.epam.viktoryia.springdatabase;

import com.epam.viktoryia.springdatabase.model.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Scanner;

public class ClientRestTemplate {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Для отправки POST-запроса нажмите 1: " +
                "\nДля отправки GET-запроса нажмите 2: " +
                "\nДля отправки PUT-запроса нажмите 3: " +
                "\nДля отправки DELETE-запроса нажмите 4:");
        try {
            while (scanner.hasNext()) {
                switch (scanner.nextInt()) {
                    case 1:
                        doPost();
                        break;
                    case 2:
                        doGet();
                        break;
                    case 3:
                        doPut();
                        break;
                    case 4:
                        doDelete();
                        break;
                    default:
                        System.out.println("Invalid.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Excseption :" + e.getMessage());
        }
    }

    private static void doPost() throws RestClientException {
        final String uri = "http://localhost:8080/employee/";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity httpEntity = new HttpEntity("[{\"id\": \"1\",\"name\": \"XXX\",\"age\": \"30\"}]", headers);
        restTemplate.postForObject(uri, httpEntity, List.class);

        System.out.println("-- response --");
    }

    private static void doGet() {
        final String uri = "http://localhost:8080/employee/";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        List <Employee> response = restTemplate.getForObject(uri, List.class);

        System.out.println("-- response --");
        System.out.println("Get response: " + response);
    }

    private static void doPut() throws RestClientException {
        final String uri = "http://localhost:8080/employee/1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        HttpEntity <String> httpEntity = new HttpEntity("\"uuu\"", headers);
        restTemplate.put(uri, httpEntity);

        System.out.println("PUT-запрос выполнен ");
    }

    private static void doDelete() {
        final String uri = "http://localhost:8080/employee/1";

        restTemplate.delete(uri);

        System.out.println("DELETE-запрос выполнен ");
    }
}