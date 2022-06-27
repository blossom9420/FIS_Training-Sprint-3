package com.pluralsight.persistence.module01.demo03;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Transactional
public class BookDAO {
    private EntityTransaction transaction;

    /**
     * Gets an entity manager
     */
    //private static EntityManagerFactory emf;
    //@Autowired
    private EntityManager em;

    public BookDAO(EntityManager em){
        this.em = em;
        this.transaction = this.em.getTransaction();
    }

    //TODO Check...
    /**
     * Persists the book to the database
     */
    public void persistBook(Book book) {

        this.transaction.begin();
        this.em.persist(book);
        this.transaction.commit();
    }

    /**
     * Finds the book from the database
     */
    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll(){
        //Query Language
        Query query = em.createQuery("FROM Book");
        return query.getResultList();
    }

    public void removeBook(Book book) {
        Book bookToBeDeleted = em.merge(book);
        transaction.begin();
        em.remove(bookToBeDeleted);
        transaction.commit();
    }

    //@Transactional
    public Book raiseUnitCost(Long id, Float raise) {
        //attach with DB
        Book book = em.find(Book.class, id);

        if (book != null) {
            //transaction.begin();
            //Update book
            book.setUnitCost(book.getUnitCost() + raise);
            //transaction.commit();
        }
        return book;
    }

}