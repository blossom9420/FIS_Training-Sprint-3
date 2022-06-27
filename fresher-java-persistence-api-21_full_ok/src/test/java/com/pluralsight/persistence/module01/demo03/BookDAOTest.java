package com.pluralsight.persistence.module01.demo03;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootTest
class BookDAOTest {

    @Autowired
    private ApplicationContext context;

    private BookDAO bookDAO;

    @BeforeEach
    void initBookDAO(){
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        this.bookDAO = new BookDAO(entityManager);
        System.out.println("initBookDAO");
    }

    @Test
    void persistBook() {
        Book book = new Book(5045L, "H2G2", "Best IT Scifi Book",
                12.5f, "1234-5678-5678", 247);
        //Try
        bookDAO.persistBook(book);
    }

    @Test
    void findBook() {
        //Try
        Book book = bookDAO.findBook(5044L);
        System.out.println("# " + book);
    }

    @Test
    void findAll() {
        //Try
        System.out.println(bookDAO.findAll());
    }

    @Test
    void removeBook() {
        //Try
        Book book = new Book(5045L, "H2G2", "Best IT Scifi Book",
                12.5f, "1234-5678-5678", 247);
        //Try
        bookDAO.removeBook(book);
    }

    @Test
    void raiseUnitCost() {
        //Try

        Book book = bookDAO.raiseUnitCost(5045L, 2.5f);
        System.out.println(book);
    }

    @AfterEach
    void clearBookDAO(){
        this.bookDAO = null;
        System.out.println("clearBookDAO");
    }
}