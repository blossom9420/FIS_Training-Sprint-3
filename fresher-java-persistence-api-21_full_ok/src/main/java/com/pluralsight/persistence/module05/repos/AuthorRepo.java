package com.pluralsight.persistence.module05.repos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pluralsight.persistence.module05.model.Author;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorRepo implements IAuthorRepo {

    private EntityManager entityManager;

    @Autowired
    public AuthorRepo(EntityManagerFactory entityManagerFactory){
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = entityManager.createNamedQuery(Author.FIND_ALL, Author.class);
        return query.getResultList();
    }
}
