package com.pluralsight.persistence.module05;

import com.pluralsight.persistence.module05.repos.AuthorRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication
public class Module5DemoApp {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    ApplicationContext context = SpringApplication.run(Module5DemoApp.class, args);

    Arrays.stream(context.getBeanDefinitionNames()).forEach(item -> System.out.println(item));

    AuthorRepo repo = (AuthorRepo)context.getBean("authorRepo");
    System.out.println(repo.getAll());
  }
}