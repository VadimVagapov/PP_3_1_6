package com.example.spring_data_rest;

import com.example.spring_data_rest.web.Communication;
import com.example.spring_data_rest.web.User;
import com.example.spring_data_rest.web.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Pp316Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        String allUsers = communication.getAllUsers();
        System.out.println(allUsers);
        System.out.println(communication.getCookie());
        User newUser = new User(3L,  "James", "Brown", (byte) 26);
        String one = communication.saveUser(newUser);
        User updateUser = new User(3L, "Thomas", "Shelby", (byte) 26);
        String two = communication.updateUser(updateUser);
        String three = communication.deleteUser(3L);
        System.out.println(one+two+three);
        System.out.println("Finished");
    }

}
