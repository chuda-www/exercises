package com.epam.viktoryia.springdatabase;

import com.epam.viktoryia.springdatabase.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestClientException;
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

    private ArgumentCaptor <HttpEntity> httpEntityCaptor = ArgumentCaptor.forClass(HttpEntity.class);

    @Test
    public void testDoPost() throws Exception {

        clientRestTemplate.doPost();
        verify(restTemplate).postForObject(Mockito.eq(URL), httpEntityCaptor.capture(), Mockito.eq(List.class));
        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        List <Employee> body = (List <Employee>) capturedHttpEntity.getBody();
        Assert.assertEquals("aaa", body.get(0).getName());
        Assert.assertEquals(new Integer(20), body.get(0).getAge());
    }

    @Test
    public void testDoGet() throws Exception {

        clientRestTemplate.doGet();
        verify(restTemplate, times(1)).getForObject(URL, List.class);
    }

    @Test
    public void testDoPut() throws Exception {

        clientRestTemplate.doPut();
        verify(restTemplate, times(1)).put(Mockito.eq(URL + ID), httpEntityCaptor.capture());
        HttpEntity capturedHttpEntity = httpEntityCaptor.getValue();
        Object body = capturedHttpEntity.getBody();
        Assert.assertEquals("\"uuu\"", body);
    }

    @Test
    public void testDoDelete() throws Exception {

        clientRestTemplate.doDelete();
        verify(restTemplate, times(1)).delete(URL + ID);
    }

    @Test(expected = RestClientException.class)
    public void testRestClientException() throws Exception {
        when(restTemplate.postForObject(Mockito.eq(URL), Mockito.any(), Mockito.eq(List.class))).thenThrow(new RestClientException("Exception"));
        clientRestTemplate.doPost();
    }
}
