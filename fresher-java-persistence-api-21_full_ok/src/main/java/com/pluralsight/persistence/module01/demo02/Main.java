package com.pluralsight.persistence.module01.demo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        System.out.println("\n\n>>> Executing : " + Main.class.toString() + " <<<\n");

        EntityManagerFactory emf = context.getBean(EntityManagerFactory.class);






        EntityManager em = emf.createEntityManager();
        BookDAO bookDAO = new BookDAO(em);

        //save
        bookDAO.persistBook(new Book(5045L, "H2G2", "Best IT Scifi Book", 12.5f, "1234-5678-5678", 247));

        //find
        Book book = bookDAO.findBook(5044L);
        System.out.println("# " + book);
    }
}