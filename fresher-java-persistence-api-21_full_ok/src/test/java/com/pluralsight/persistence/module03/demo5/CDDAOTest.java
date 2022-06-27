package com.pluralsight.persistence.module03.demo5;

import com.pluralsight.persistence.module03.model.CD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@SpringBootTest
public class CDDAOTest {
    @Autowired
    private ApplicationContext context;

    private CDDAO cdDAO;

    @BeforeEach
    void initBookDAO(){
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        this.cdDAO = new CDDAO(entityManager);
    }
    @Test
    void persistCD() {
    }

    @Test
    void findBook() {
        CD cd = cdDAO.findCD(1L);
        System.out.println(cd);
        cd.getMusicians().stream().forEach(
            musician -> {
                System.out.println(musician.getName() + " = " + musician.getCds().size());
            }
        );
    }
}