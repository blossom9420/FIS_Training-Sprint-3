package com.pluralsight.persistence.module03.demo5;

import com.pluralsight.persistence.module03.model.Musician;

import javax.persistence.EntityManager;

public class MusicianDAO {
    private EntityManager em;

    public MusicianDAO (EntityManager em){
        this.em = em;
    }

    public void persistMusician(Musician musician) {
        em.getTransaction().begin();
        em.persist(musician);
        em.getTransaction().commit();
    }

    public Musician findMusician(Long id) {
        return em.find(Musician.class, id);
    }
}
