package com.example.spring_data_rest.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class Communication {

    @Autowired
    private RestTemplate restTemplate;
    private String URL = "http://94.198.50.185:7081/api/users";

    private String cookie;

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getAllUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity =
        restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);
        cookie = responseEntity.getHeaders().getFirst("Set-Cookie");
        return responseEntity.getBody();
    }


    public String saveUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<>(user,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    public String updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Cookie", cookie);
        HttpEntity<User> entity = new HttpEntity<User>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange((URL + "/" + id), HttpMethod.DELETE, entity, String.class);
        return responseEntity.getBody();
    }
}
