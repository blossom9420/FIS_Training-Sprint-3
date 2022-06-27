package com.m6.javadevjournal.springbooth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

//@SpringBootApplication(exclude = SqlInitializationAutoConfiguration.class)
@SpringBootApplication
public class SpringBootH2Application {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootH2Application.class, args);
  }

}
