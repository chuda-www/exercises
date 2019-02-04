package com.epam.viktoryia.springdatabase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ClientRestTemplateTest extends Mockito {

    private static final Integer ID = 1;
    private static final String URL = "http://localhost:8080/employee/";

    @Mock
    RestTemplate restTemplate;
    @InjectMocks
    ClientRestTemplate clientRestTemplate;
    @Captor
    ArgumentCaptor <HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);

    @Test
    public void testDoPost() throws Exception {

        clientRestTemplate.doPost();
        verify(restTemplate).postForObject(Mockito.eq(URL), httpEntityCaptor.capture(), Mockito.eq(List.class));
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
