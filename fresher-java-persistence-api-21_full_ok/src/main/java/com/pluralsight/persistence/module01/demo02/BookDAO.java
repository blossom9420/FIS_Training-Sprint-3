package com.pluralsight.persistence.module01.demo02;

import javax.persistence.EntityManager;

public class BookDAO {
    /**
     * Gets an entity manager
     */
    //private static EntityManagerFactory emf;
    //@Autowired
    private EntityManager em;

    public BookDAO (EntityManager em){
        this.em = em;
        //this.em = em;
    }

    //TODO Check...
    /**
     * Persists the book to the database
     */
    public void persistBook(Book book) {
        em.getTransaction().begin();
        em.persist(book);
        em.getTransaction().commit();
    }

    /**
     * Finds the book from the database
     */
    public Book findBook(Long id) {
        return em.find(Book.class, id);
    }
}