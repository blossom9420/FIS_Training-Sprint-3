package com.pluralsight.persistence.module04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class Module4DemoApp {

  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    ApplicationContext context = SpringApplication.run(Module4DemoApp.class, args);

    //new M4DemoMain(context).test();
  }
}