package com.pluralsight.persistence.module03.demo5;

import com.pluralsight.persistence.module03.model.CD;
import com.pluralsight.persistence.module03.model.Musician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootTest
public class MusicianDAOTest {
    @Autowired
    private ApplicationContext context;

    private MusicianDAO musicianDAO;

    @BeforeEach
    void initBookDAO(){
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        this.musicianDAO = new MusicianDAO(entityManager);
    }
    @Test
    void persistCD() {
    }

    @Test
    void findBook() {
        Musician musician = musicianDAO.findMusician(1L);
        System.out.println(musician);


    }
}